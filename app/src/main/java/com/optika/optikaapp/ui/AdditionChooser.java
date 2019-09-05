package com.optika.optikaapp.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.optika.optikaapp.R;

public class AdditionChooser {

    TextView diopterValue;
    Button increaseWhole;
    Button increaseQuarter;
    Button decreaseWhole;
    Button decreaseQuarter;

    public AdditionChooser(View view) {
        diopterValue = view.findViewById(R.id.diopter);
        increaseWhole = view.findViewById(R.id.button_increase_whole);
        increaseQuarter = view.findViewById(R.id.button_increase_quarter);
        decreaseWhole = view.findViewById(R.id.button_decrease_whole);
        decreaseQuarter = view.findViewById(R.id.button_decrease_quarter);
    }

}
