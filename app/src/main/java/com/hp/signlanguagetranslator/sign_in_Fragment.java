package com.hp.signlanguagetranslator;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in_Fragment extends Fragment {
    TextView signup;
    Button signin;
    TextInputEditText phonef,pwdf;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public sign_in_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_, container, false);
        signup = view.findViewById(R.id.signup);
        signup.setText(Html.fromHtml("<font>Don't have an Account? </font><font color='yellow'>Sign Up</font>"));
        signin = view.findViewById(R.id.signin);
        signup.setOnClickListener(v -> MainActivity.clicked(0,getActivity()));
        phonef = view.findViewById(R.id.phonef);
        pwdf = view.findViewById(R.id.pwdf);
        signin.setOnClickListener(v -> signinCalled());
        return view;
    }

    private void signinCalled() {
        String phone,email,pwd;
        phone = phonef.getText().toString().trim();
        pwd = pwdf.getText().toString().trim();
        String phone_no = "+91"+phone;
        email = phone_no + "@signLanguageTranslator.com";
        Log.i("heetslt",String.valueOf(email));
        Log.i("heetslt",String.valueOf(pwd));
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getActivity(),FinalActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}