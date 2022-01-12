package com.hp.signlanguagetranslator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<com.hp.signlanguagetranslator.viewHolder> {
    Context context;
    ArrayList<com.hp.signlanguagetranslator.imageModel> imageModels;

    public imageAdapter(Context context, ArrayList<com.hp.signlanguagetranslator.imageModel> imageModels){
        this.context = context;
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public com.hp.signlanguagetranslator.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_setup,parent,false);
        return new com.hp.signlanguagetranslator.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.hp.signlanguagetranslator.viewHolder holder, int position) {
        com.hp.signlanguagetranslator.imageModel imageModel = imageModels.get(position);
        Glide.with(context).load(imageModel.getUri()).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).placeholder(R.drawable.ic_launcher_background).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }
}

