package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("id")
    private int id;

    @SerializedName("phoneNum")
    private String phoneNum;

    @SerializedName("isDeleted")
    private boolean isDeleted;

    @SerializedName("buyer")
    private int buyer;

    public String getPhoneNum() {
        if(isDeleted == true) {
            return " ";
        } else {
            return phoneNum;
        }
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Contact(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }
}
