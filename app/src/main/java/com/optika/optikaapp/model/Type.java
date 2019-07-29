package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("id_tip")
    private int id;

    @SerializedName("tip")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
