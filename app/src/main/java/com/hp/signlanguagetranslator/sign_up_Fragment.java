package com.hp.signlanguagetranslator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class sign_up_Fragment extends Fragment {
    static TextInputEditText namef,phonef,pwdf,rpwdf;
    static TextInputLayout pwdl,rpwdl,namel,phonel;
    TextView signin;
    Button signup;
    public sign_up_Fragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_, container, false);
        signin = view.findViewById(R.id.haveaccount);
        signin.setText(Html.fromHtml("<font>Already have an Account? </font><font color='yellow'>Sign In</font>"));
        signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(v -> signupCalled());
        signin.setOnClickListener(v -> MainActivity.clicked(1,getActivity()));
        namef = view.findViewById(R.id.namef);
        phonef = view.findViewById(R.id.phonef);
        pwdf = view.findViewById(R.id.pwdf);
        rpwdf = view.findViewById(R.id.rpwdf);
        pwdl = view.findViewById(R.id.pwd);
        rpwdl = view.findViewById(R.id.rpwd);
        namel = view.findViewById(R.id.name);
        phonel = view.findViewById(R.id.phone);
        pwdf.setOnClickListener(v -> pwdl.setPasswordVisibilityToggleEnabled(true));
        rpwdf.setOnClickListener(v -> rpwdl.setPasswordVisibilityToggleEnabled(true));
        return view;
    }

    private void signupCalled() {
        String name,phone,pwd,rpwd;
        name = namef.getText().toString().trim();
        phone = phonef.getText().toString().trim();
        pwd = pwdf.getText().toString().trim();
        rpwd = rpwdf.getText().toString().trim();

        if(!validateName(name) | !validatePhone(phone) | !validatePwd(pwd,rpwd)){

        }
        else{
            MainActivity.signUpCalled(name,phone,pwd,getActivity());
        }
    }

    private static boolean validateName(String name) {
        if(name.isEmpty()){
            namef.setError("Name must not be empty");
            return false;
        }
        else
            namef.setError(null);
            return true;
    }

    private static boolean validatePwd(String password,String Cpassword)
    {
        if(password.length()<8) {
            pwdl.setPasswordVisibilityToggleEnabled(false);
            pwdf.setError("Password must be 8 character long.");
            return false;
        }
        else if(!Utils.PASSWORD_PATTERN.matcher(password).matches()){
            pwdl.setPasswordVisibilityToggleEnabled(false);
            pwdf.setError("Minimum 1 lower & upper case, digit & special character required.");
            return false;
        }
        else{
            pwdl.setPasswordVisibilityToggleEnabled(true);
            pwdf.setError(null);
            if(!password.equals(Cpassword)){
                rpwdl.setPasswordVisibilityToggleEnabled(false);
                rpwdf.setError("Confirm Password must be same as Password");
                return false;
            }
            else{
                rpwdl.setPasswordVisibilityToggleEnabled(true);
                rpwdf.setError(null);
                return true;
            }
        }
    }

    private static boolean validatePhone(String phone_no)
    {
        if(phone_no.length()!=10){
            phonef.setError("Invalid Phone No.");
            return false;
        }
        else{
            phonef.setError(null);
            return true;
        }
    }
}