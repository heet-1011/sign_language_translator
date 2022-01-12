package com.hp.signlanguagetranslator;

import android.widget.Button;

public class phrasesModel {
    String phrases;

    public phrasesModel(String phrases, Button button) {
        this.phrases = phrases;
    }

    public String getPhrases() {
        return phrases;
    }

    public void setPhrases(String phrases) {
        this.phrases = phrases;
    }

}
