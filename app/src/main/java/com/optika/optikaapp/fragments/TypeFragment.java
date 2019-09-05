package com.optika.optikaapp.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.optika.optikaapp.R;
import com.optika.optikaapp.activities.NewBuyerOrder;
import com.optika.optikaapp.helpers.Saveable;
import com.optika.optikaapp.helpers.SaveableWithAddition;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends Fragment {

    SaveableWithAddition parent;
    ToggleButton toggleButton;

    public TypeFragment() {
        // Required empty public constructor
    }

    public TypeFragment(SaveableWithAddition parent) {
        this.parent = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        toggleButton = view.findViewById(R.id.type_of_glasses_xml);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton.getText().equals(getResources().getString(R.string.naocare_za_rad))) {
                    parent.additionOff();
                } else {
                    parent.additionOn();
                }
            }
        });
        return view;
    }

    public Boolean getType() {
        return toggleButton.getText().equals(getResources().getString(R.string.naocare_za_stalno)) ? Boolean.TRUE : Boolean.FALSE;
    }
}
