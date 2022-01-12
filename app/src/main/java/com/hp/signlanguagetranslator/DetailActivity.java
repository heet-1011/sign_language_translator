package com.hp.signlanguagetranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class DetailActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    static Fragment f;
    String phone_no,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        frameLayout = findViewById(R.id.fl);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new user_details1_fragment()).commit();
        phone_no = getIntent().getStringExtra("phone_no");
        name = getIntent().getStringExtra("name");
    }

    public static void clicked(int i, FragmentActivity activity) {
        switch (i){
            case 0 : f = new user_details1_fragment();
                break;
            case 1 : f = new user_details2_fragment();
                break;
        }
        activity.getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fl,f).commit();
    }
}