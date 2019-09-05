package com.optika.optikaapp.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.optika.optikaapp.R;

public class DiopterChooser extends AdditionChooser {

    ToggleButton diopterPosNeg;
    TextView diopterName;

    public DiopterChooser(View view) {
        super(view);
        diopterPosNeg = view.findViewById(R.id.toggle_btn);
        diopterName = view.findViewById(R.id.diopter_id);
        attachListenerIncreaseWhole(increaseWhole);
        attachListenerIncreaseQuarter(increaseQuarter);
        attachListenerDecreaseWhole(decreaseWhole);
        attachListenerDecreaseQuarter(decreaseQuarter);
    }

    private void attachListenerIncreaseWhole(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d += 1.0;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerIncreaseQuarter(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d += 0.25;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerDecreaseWhole(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d -= 1.0;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }

    private void attachListenerDecreaseQuarter(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d = Double.valueOf(diopterValue.getText().toString());
                d -= 0.25;
                diopterValue.setText(String.valueOf(d));
            }
        });
    }
}


