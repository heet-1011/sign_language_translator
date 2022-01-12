package com.hp.signlanguagetranslator;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hp.signlanguagetranslator.classifier.ImageClassifier;

public class recycler_fragment extends Fragment {
    RecyclerView recView;
    imageAdapter imageAdapter;
    private ImageClassifier imageClassifier;
    String Final="";

    public recycler_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        recView = view.findViewById(R.id.recView);
        Log.i("heetslt","recview init");
        imageAdapter = new imageAdapter(getContext(),getData());
        Log.i("heetslt","adapter init");
        recView.setLayoutManager(new GridLayoutManager(getContext(),3));
        Log.i("heetslt","layout set");
        recView.setAdapter(imageAdapter);
        Log.i("heetslt","adapter set");
        return view;
    }

    private ArrayList<imageModel> getData(){
        ArrayList<imageModel> imageModels = new ArrayList<>();
        File folder = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Sign Language Translator");
        imageModel s;
        Log.i("heetslt",String.valueOf(folder.getPath()));
        if (folder.exists()) {
            Log.i("heetslt","folder exist");
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                s = new imageModel();
                Log.i("heetslt", String.valueOf(Uri.fromFile(file)));
                s.setUri(Uri.fromFile(file));
                try {
                    imageClassifier = new ImageClassifier(getActivity());
                    Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(file));
                    List<ImageClassifier.Recognition> predicitons = imageClassifier.recognizeImage(photo, 0);
                    List<String> arr = new ArrayList<>();
                    List<Float> arr1 = new ArrayList<>();
                    for (ImageClassifier.Recognition recognition : predicitons) {
                        arr.add(recognition.getName() + " ");
                        arr1.add(recognition.getConfidence());
                    }
                        //MainActivity.add(arr,arr1);
                    value(arr, arr1);
                } catch (IOException e) {
                    Log.e("Image Classifier Error", "ERROR: " + e);
                }
                    //final List<String> predicitonsList = new ArrayList<>();
                    //for (ImageClassifier.Recognition recog : predicitons) {predicitonsList.add(recog.getName() + "  ::::::::::  " + recog.getConfidence());
                    //ArrayAdapter<String> predictionsAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, predicitonsList);
                    //MainActivity.prediction(predictionsAdapter);
                imageModels.add(s);
            }
            FinalActivity.sendValue(Final);
        }
        return imageModels;
    }

    public void value(List<String> arr,List<Float> arr1){
        Log.i("heetslt",String.valueOf(arr));
        Float item = Collections.max(arr1);
        int index = arr1.indexOf(item);
        Log.i("heetslt",String.valueOf(arr.get(index)));
        Final = Final + arr.get(index);
        Log.i("heetslt",String.valueOf(Final));
    }

}