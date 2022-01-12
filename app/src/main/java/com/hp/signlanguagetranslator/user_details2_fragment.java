package com.hp.signlanguagetranslator;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class user_details2_fragment extends Fragment {
    TextInputLayout language,nationality,slanguage;
    ImageView back;
    Button next;
    AutoCompleteTextView languageACTV,nationalityACTV,slanguageACTV;
    public user_details2_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details2, container, false);
        language = view.findViewById(R.id.language);
        languageACTV = view.findViewById(R.id.languageACTV);
        List<String> list = new ArrayList<>();
        list.add("English");
        list.add("Hindi");
        list.add("Gujarati");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        languageACTV.setAdapter(genderAdapter);
        nationality = view.findViewById(R.id.nationality);
        nationalityACTV = view.findViewById(R.id.nationalityACTV);
        List<String> list2 = new ArrayList<>();
        list2.add("India");
        list2.add("Sri Lanka");
        list2.add("United States of America");
        ArrayAdapter<String> genderAdapter2 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list2);
        nationalityACTV.setAdapter(genderAdapter2);
        slanguage = view.findViewById(R.id.slanguage);
        slanguageACTV = view.findViewById(R.id.slanguageACTV);
        List<String> list3 = new ArrayList<>();
        list3.add("Indian Sign Language");
        list3.add("British Sign Language");
        list3.add("American Sign Language");
        ArrayAdapter<String> genderAdapter3 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list3);
        slanguageACTV.setAdapter(genderAdapter3);
        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);
        back.setOnClickListener(v -> DetailActivity.clicked(0,getActivity()));
        next.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),FinalActivity.class);
            getActivity().startActivity(intent);
        });
        return view;
    }
}