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
public class BuyerFragment extends Fragment {

    EditText name;
    EditText lastname;
    EditText phoneNum;

    public BuyerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);
        name = view.findViewById(R.id.name_input_new);
        lastname = view.findViewById(R.id.lastname_input_new);
        phoneNum = view.findViewById(R.id.phone_num_input_new);
        return view;
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getLastname() {
        return lastname.getText().toString();
    }

    public String getPhoneNum() {
        return phoneNum.getText().toString();
    }

}
