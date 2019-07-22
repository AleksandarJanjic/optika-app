package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("phoneNum")
    private String phoneNum;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Contact(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
