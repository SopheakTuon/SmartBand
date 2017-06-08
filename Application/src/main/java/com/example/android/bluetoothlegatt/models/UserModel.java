package com.example.android.bluetoothlegatt.models;

import java.io.Serializable;
import java.util.Calendar;

public class UserModel implements Serializable {
    private String adornHand;
    private String birthday;
    private String diastolic;
    private String distanceUnit;
    private String height;
    private String sex;
    private String systaltic;
    private String weight;
    private String weightUint;

    public String getDistanceUnit() {
        return this.distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public String getDiastolic() {
        return this.diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getSystaltic() {
        return this.systaltic;
    }

    public void setSystaltic(String systaltic) {
        this.systaltic = systaltic;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAdornHand() {
        return this.adornHand;
    }

    public void setAdornHand(String adornHand) {
        this.adornHand = adornHand;
    }

    public String toString() {
        return "UserModel{sex='" + this.sex + '\'' + ", birthday='" + this.birthday + '\'' + ", weight='" + this.weight + '\'' + ", height='" + this.height + '\'' + ", adornHand='" + this.adornHand + '\'' + ", diastolic='" + this.diastolic + '\'' + ", systaltic='" + this.systaltic + '\'' + ", distanceUnit='" + this.distanceUnit + '\'' + '}';
    }

    public int getAge() {
        if (getBirthday() == null) {
            return 0;
        }
        return (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(getBirthday().substring(0, 4))) + 1;
    }

    public String getWeightUint() {
        return this.weightUint;
    }

    public void setWeightUint(String weightUint) {
        this.weightUint = weightUint;
    }
}
