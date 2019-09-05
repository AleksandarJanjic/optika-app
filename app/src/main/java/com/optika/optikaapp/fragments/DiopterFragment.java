package com.optika.optikaapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.optika.optikaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiopterFragment extends Fragment {

    TextView diopterName;
    TextView diopterValue;
    ImageButton increaseWhole;
    ImageButton increaseQuarter;
    ImageButton decreaseWhole;
    ImageButton decreaseQuarter;
    String name;

    public DiopterFragment(String name) {
        this.name = name;
    }

    public DiopterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diopter, container, false);
        diopterName = view.findViewById(R.id.diopter_id);
        diopterName.setText(name);
        diopterValue = view.findViewById(R.id.diopter);
        increaseWhole = view.findViewById(R.id.button_increase_whole);
        increaseQuarter = view.findViewById(R.id.button_increase_quarter);
        decreaseWhole = view.findViewById(R.id.button_decrease_whole);
        decreaseQuarter = view.findViewById(R.id.button_decrease_quarter);
        attachListenerIncreaseWhole(increaseWhole);
        attachListenerIncreaseQuarter(increaseQuarter);
        attachListenerDecreaseWhole(decreaseWhole);
        attachListenerDecreaseQuarter(decreaseQuarter);
        return view;
    }

    private void attachListenerIncreaseWhole(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d += 1.0;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerIncreaseQuarter(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d += 0.25;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerDecreaseWhole(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d -= 1.0;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerDecreaseQuarter(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d -= 0.25;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    public Double getDiopter() {
        return Double.valueOf(diopterValue.getText().toString());
    }
}
