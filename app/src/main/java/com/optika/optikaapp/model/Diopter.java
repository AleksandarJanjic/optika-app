package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

public class Diopter {

    @SerializedName("iddioptrija")
    private int id;

    @SerializedName("diopter")
    private double diopter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiopter() {
        Double diop = (Double) diopter;
        if(diop == null) {
            return "0.00";
        }
        else {
            if(diop < 0) {
                return Double.toString(diopter);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("+");
                stringBuilder.append(Double.toString(diopter));
                return stringBuilder.toString();
            }
        }
    }

    public void setDiopter(double diopter) {
        this.diopter = diopter;
    }
}
