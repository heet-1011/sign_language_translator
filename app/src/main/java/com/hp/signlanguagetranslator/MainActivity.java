package com.hp.signlanguagetranslator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    private static Context context;
    FrameLayout frameLayout;
    static Fragment f;
    static String Phone_No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        frameLayout = findViewById(R.id.fl);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, new sign_in_Fragment()).commit();
    }

    public static void clicked(int i, FragmentActivity activity) {
        switch (i) {
            case 0:
                f = new sign_up_Fragment();
                break;
            case 1:
                f = new sign_in_Fragment();
                break;
        }
        activity.getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fl, f).commit();
    }

    public static void signUpCalled(String name, String phone, String pwd, FragmentActivity activity) {
        Phone_No = "+91" + phone;
        f = new otp_Fragment(name, Phone_No, pwd);
        activity.getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fl, f).commit();
    }

    public static void authenticate(String name) {
        if (name == null) {
            Intent intent = new Intent(context, FinalActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("phone", Phone_No);
            intent.putExtra("name", name);
            context.startActivity(intent);
        }
    }
}