package com.hp.signlanguagetranslator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.hp.signlanguagetranslator.classifier.ImageClassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FinalActivity extends AppCompatActivity {

    ImageView toggle;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MotionLayout motionLayout;
    DrawerLayout drawerLayout;
    static final float END_SCALE = 0.95f;
    ConstraintLayout constraintLayout;
    private ImageClassifier imageClassifier;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1000;
    private static final int CAMERA_REQEUST_CODE = 10001;
    Button btn_camera;
    ImageButton btn_speech;
    TextToSpeech textToSpeech;
    static TextView textView;
    TextView textDeveloper, textDeveloper0;
    NavigationView navigationView;
    ImageView backAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toggle = findViewById(R.id.hamburger_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        constraintLayout = findViewById(R.id.content_main);
        navigationView = findViewById(R.id.navigation_view);
        textDeveloper = findViewById(R.id.textdeveloper);
        textDeveloper0 = findViewById(R.id.textdeveloper0);
        motionLayout = findViewById(R.id.about_us);
        backAbout = findViewById(R.id.back_icon);
        btn_speech = findViewById(R.id.btn_speech);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int language = textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.setSpeechRate(1);
                    textToSpeech.setPitch(1);
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.flayout, new recycler_fragment()).commit();
        setSupportActionBar(toolbar);
        toggle.setOnClickListener(v -> {
            if (!drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START);
            } else {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        animateLayout();
        try {
            imageClassifier = new ImageClassifier(this);
        } catch (IOException e) {
            Log.e("Image Classifier Error", "ERROR: " + e);
        }
        btn_camera = findViewById(R.id.btn_camera);
        textView = findViewById(R.id.text);
        btn_camera.setOnClickListener(v -> {
            if (hasPermission()) {
                //openCamera();
                Intent intent = new Intent(FinalActivity.this, CameraActivity.class);
                startActivity(intent);
                finish();
            } else {
                requestPermission();
            }
        });
        btn_speech.setOnClickListener(v -> {
            String s = textView.getText().toString();
            int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.fphrases:
                        Intent intent = new Intent(FinalActivity.this, FPhrasesActivity.class);
                        startActivity(intent);
                        finish();
                    case R.id.about:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        motionLayout.transitionToEnd();
                        motionLayout.setClickable(true);
                        break;
                    case R.id.signout:
                        mAuth.signOut();
                        Intent intent1 = new Intent(FinalActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    default:
                        break;
                }
                return true;
            }
        });
        textDeveloper.setMovementMethod(LinkMovementMethod.getInstance());
        textDeveloper0.setMovementMethod(LinkMovementMethod.getInstance());
        backAbout.setOnClickListener(v -> motionLayout.transitionToStart());
    }

    private void animateLayout() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                constraintLayout.setScaleX(offsetScale);
                constraintLayout.setScaleY(offsetScale);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (motionLayout.getCurrentState() == motionLayout.getEndState()) {
            motionLayout.transitionToStart();
        } else {
            super.onBackPressed();
        }
    }

    private boolean hasAllPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendValue(String Final) {
        List<String> list = new ArrayList<String>(Arrays.asList(Final.split(" ")));
        Collections.reverse(list);
        String output = String.join(" ", list);
        textView.setText(output);

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // if this is the result of our camera permission request
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (hasAllPermissions(grantResults)) {
                //openCamera();
                Intent intent = new Intent(FinalActivity.this, CameraActivity.class);
                startActivity(intent);
                finish();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File folder = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Sign Language Translator");
        if (folder.listFiles() != null) {
            File[] files = folder.listFiles();
            for (File tmp : files)
                tmp.delete();
        }
    }

}