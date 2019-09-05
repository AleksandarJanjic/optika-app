package com.optika.optikaapp.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.optika.optikaapp.R;
import com.optika.optikaapp.activities.NewBuyerOrder;
import com.optika.optikaapp.helpers.Saveable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment {

    Button saveButton;
    Saveable parent;

    public SaveFragment() {
        // Required empty public constructor
    }

    public SaveFragment(Saveable parent) {
        this.parent = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        saveButton = view.findViewById(R.id.save_order_new);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.save();
            }
        });
        return view;
    }

}
