package com.optika.optikaapp.helpers;

import com.optika.optikaapp.model.Angle;
import com.optika.optikaapp.model.Diopter;

public class CheckForNull {

    public static String checkIfNull(Diopter diopter) {
        if(diopter == null) {
            return "0.00";
        }
        else {
            return diopter.getDiopter();
        }
    }

    public static String checkIfNull(Angle angle) {
        if(angle == null) {
            return " ";
        }
        else {
            return angle.getAngle();
        }
    }
}
