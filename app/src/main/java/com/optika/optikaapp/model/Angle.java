package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

public class Angle {

    @SerializedName("idugao")
    private int id;

    @SerializedName("angle")
    private int angle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAngle() {
        return String.valueOf(angle);
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
