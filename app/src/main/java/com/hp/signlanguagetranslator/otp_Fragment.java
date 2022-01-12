package com.hp.signlanguagetranslator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_Fragment extends Fragment {
    TextView resend;
    Button go;
    ImageView back;
    String name,phone_no,pwd,systemcode,email;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText editText1,editText2,editText3,editText4,editText5,editText6;

    public otp_Fragment() {
    }

    public otp_Fragment(String name, String phone_no, String pwd) {
        this.name = name;
        this.phone_no = phone_no;
        this.pwd = pwd;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_, container, false);
        resend = view.findViewById(R.id.resend);
        resend.setText(Html.fromHtml("<font>Didn't Received OTP? </font><font color='yellow'>Resend OTP</font>"));
        go = view.findViewById(R.id.go);
        go.setOnClickListener(v -> clickbutton(view));
        back = view.findViewById(R.id.back);
        back.setOnClickListener(v -> MainActivity.clicked(1,getActivity()));
        sendotp(phone_no);
        Log.i("heet",String.valueOf(phone_no));
        editText1 = view.findViewById(R.id.et_otp_1);
        editText2 = view.findViewById(R.id.et_otp_2);
        editText3 = view.findViewById(R.id.et_otp_3);
        editText4 = view.findViewById(R.id.et_otp_4);
        editText5 = view.findViewById(R.id.et_otp_5);
        editText6 = view.findViewById(R.id.et_otp_6);
        return view;
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            systemcode = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String smscode = phoneAuthCredential.getSmsCode();
            if(!smscode.isEmpty()){
                verifycode();
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    };

    private void sendotp(String phone_no){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone_no)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(getActivity())
                        .setCallbacks(callback)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void clickbutton(View view){
        String code = editText1.getText().toString().trim() + editText2.getText().toString().trim() + editText3.getText().toString().trim() +
                editText4.getText().toString().trim() + editText5.getText().toString().trim() + editText6.getText().toString().trim();
        if(!code.isEmpty()){
            verifycode();
        }
    }

    private void verifycode() {
        //PhoneAuthCredential credential = PhoneAuthProvider.getCredential(systemcode, code);
        //signInTheUserByCredentials(credential);
        email = phone_no + "@signLanguageTranslator.com";
        createUserAccount(email,pwd);
    }

    private void createUserAccount(String email, String pwd) {
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        function();
                    } else {
                        Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void function() {
        //MainActivity.authenticate(name);
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        //intent.putExtra("phone",Phone_No);
        intent.putExtra("name",name);
        intent.putExtra("phone_no",phone_no);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    /*private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {

                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

}