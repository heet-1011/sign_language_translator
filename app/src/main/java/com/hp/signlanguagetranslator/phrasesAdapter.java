package com.hp.signlanguagetranslator;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class phrasesAdapter extends RecyclerView.Adapter<phrasesAdapter.viewholder> {
    Context context;
    private List<String> phraseList;


    public phrasesAdapter(Context context, List<String> phraseList) {
        this.context = context;
        this.phraseList = phraseList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.phrases_view,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.phrase.setText(phraseList.get(position));
        holder.btn.setOnClickListener(v -> {
            FPhrasesActivity.text_to_speech(phraseList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{
        TextView phrase;
        ImageButton btn;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            phrase = itemView.findViewById(R.id.phrase);
            btn = itemView.findViewById(R.id.btn_phrase);
        }
    }

}
