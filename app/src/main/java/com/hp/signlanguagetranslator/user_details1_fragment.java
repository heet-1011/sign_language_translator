package com.hp.signlanguagetranslator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class user_details1_fragment extends Fragment {
    private int mYear,mMonth,mDay;
    ImageButton camera;
    TextInputLayout gender,dob;
    TextInputEditText dobf,namef;
    AutoCompleteTextView genderACTV;
    Button next;
    String name;

   public user_details1_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details1, container, false);
        camera = view.findViewById(R.id.camera);
        dob = view.findViewById(R.id.dob);
        dobf = view.findViewById(R.id.dobf);
        dobf.setOnClickListener(this::onClick);
        gender = view.findViewById(R.id.gender);
        genderACTV = view.findViewById(R.id.genderACTV);
        namef = view.findViewById(R.id.namef);
        name = new DetailActivity().name;
        namef.setText(name);
        Resources res = getResources();
        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        list.add("Other");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        genderACTV.setAdapter(genderAdapter);
        next = view.findViewById(R.id.next);
        next.setOnClickListener(v -> nextClicked());
        return view;
    }

    public void onClick(View v){
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, i, i1, i2) -> {
            dobf.setText(i2+"/"+(i1+1)+"/"+i);
            SimpleDateFormat sdf = new SimpleDateFormat("EE");
            Date date = new Date(i,i1,i2-1);
        },mYear,mMonth,mDay);

        datePickerDialog.show();
    }

    public void nextClicked(){
       DetailActivity.clicked(1,getActivity());
    }
}