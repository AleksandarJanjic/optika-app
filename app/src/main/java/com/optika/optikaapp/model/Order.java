package com.optika.optikaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Order {

    @SerializedName("id")
    private int id;

    @SerializedName("date")
    private Date date;

    @SerializedName("od_sph")
    private Diopter od_sph;

    @SerializedName("os_sph")
    private Diopter os_sph;

    @SerializedName("od_cyl")
    private Diopter od_cyl;

    @SerializedName("os_cyl")
    private Diopter os_cyl;

    @SerializedName("od_angle")
    private Angle od_angle;

    @SerializedName("os_angle")
    private Angle os_angle;

    @SerializedName("pd")
    private double pd;

    @SerializedName("type")
    private Type type;

    @SerializedName("frame")
    private String frame;

    @SerializedName("comment")
    private String comment;

    @SerializedName("hasAddition")
    private Boolean hasAddition;

    @SerializedName("addition")
    private Diopter addition;

    @SerializedName("isDeleted")
    private boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Diopter getOd_sph() {
        return od_sph;
    }

    public void setOd_sph(Diopter od_sph) {
        this.od_sph = od_sph;
    }

    public Diopter getOs_sph() {
        return os_sph;
    }

    public void setOs_sph(Diopter os_sph) {
        this.os_sph = os_sph;
    }

    public Diopter getOd_cyl() {
        return od_cyl;
    }

    public void setOd_cyl(Diopter od_cyl) {
        this.od_cyl = od_cyl;
    }

    public Diopter getOs_cyl() {
        return os_cyl;
    }

    public void setOs_cyl(Diopter os_cyl) {
        this.os_cyl = os_cyl;
    }

    public Angle getOd_angle() {
        return od_angle;
    }

    public void setOd_angle(Angle od_angle) {
        this.od_angle = od_angle;
    }

    public Angle getOs_angle() {
        return os_angle;
    }

    public void setOs_angle(Angle os_angle) {
        this.os_angle = os_angle;
    }

    public String getPd() {
        return String.valueOf(pd);
    }

    public void setPd(double pd) {
        this.pd = pd;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getHasAddition() {
        return hasAddition;
    }

    public void setHasAddition(Boolean hasAddition) {
        this.hasAddition = hasAddition;
    }

    public Diopter getAddition() {
        return addition;
    }

    public void setAddition(Diopter addition) {
        this.addition = addition;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
