package com.optika.optikaapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.optika.optikaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AngleFragment extends Fragment {

    EditText angleValue;

    public AngleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_angle, container, false);
        angleValue = view.findViewById(R.id.angle_value_new);
        return view;
    }

    public Integer getAngle() {
        if(angleValue.getText().toString().isEmpty()) {
            return null;
        }
        return Integer.valueOf(angleValue.getText().toString());
    }

}
