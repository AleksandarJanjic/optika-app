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
public class ContactFragment extends Fragment {

    EditText phoneNum;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        phoneNum = view.findViewById(R.id.phone_num_input_old);
        return view;
    }

    public String getPhone() {
        return phoneNum.getText().toString();
    }
}
