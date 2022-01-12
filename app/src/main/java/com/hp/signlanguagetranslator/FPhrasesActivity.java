package com.hp.signlanguagetranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FPhrasesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> list;
    phrasesAdapter phrasesAdapter;
    ImageView imageView;
    static TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fphrases);
        recyclerView = findViewById(R.id.phrases_recView);
        imageView = findViewById(R.id.back_icon);
        imageView.setOnClickListener(v -> {
            back();
        });
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int language = textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.setSpeechRate(1);
                    textToSpeech.setPitch(1);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = Arrays.asList(getResources().getStringArray(R.array.phrase));
        recyclerView.setAdapter(new phrasesAdapter(getApplicationContext(),list));
    }

    private void back() {
        Intent intent = new Intent(FPhrasesActivity.this,FinalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public static void text_to_speech(String s) {
        int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }

}