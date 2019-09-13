package com.optika.optikaapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.optika.optikaapp.R;
import com.optika.optikaapp.helpers.DateFormatter;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    EditText pd;
    EditText lensType;
    EditText frame;
    EditText comment;
    DatePicker dateValue;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        pd = view.findViewById(R.id.pd_value_new);
        lensType = view.findViewById(R.id.lens_type_value_new);
        frame = view.findViewById(R.id.frame_value_new);
        comment = view.findViewById(R.id.comment_value_new);
        dateValue = view.findViewById(R.id.date_value_new);
        return view;
    }

    public String getPD() {
        if(pd.getText().toString().isEmpty()) {
            return "0";
        } else {
            return pd.getText().toString();
        }
    }

    public String getLensType() {
        return lensType.getText().toString();
    }

    public String getFrame() {
        return frame.getText().toString();
    }

    public String getComment() {
        return comment.getText().toString();
    }

    public String getDate() {
        return DateFormatter.getDate(dateValue.getDayOfMonth(), dateValue.getMonth(), dateValue.getYear());
    }

}
