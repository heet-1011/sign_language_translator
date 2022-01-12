package com.hp.signlanguagetranslator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder extends RecyclerView.ViewHolder {
    ImageView img;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
    }
}
