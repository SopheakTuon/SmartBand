package com.example.android.bluetoothlegatt.models;

import java.io.Serializable;

public class MainDataList implements Serializable {
    private static final long serialVersionUID = 6634617108185671753L;
    public String measureData;
    public String measuredate;
    public String measuretype;

    public String getMeasuredate() {
        return this.measuredate;
    }

    public void setMeasuredate(String measuredate) {
        this.measuredate = measuredate;
    }

    public String getMeasuretype() {
        return this.measuretype;
    }

    public void setMeasuretype(String measuretype) {
        this.measuretype = measuretype;
    }

    public String getMeasureData() {
        return this.measureData;
    }

    public void setMeasureData(String measureData) {
        this.measureData = measureData;
    }

    public MainDataList(String measuredate, String measuretype, String measureData) {
        this.measuredate = measuredate;
        this.measuretype = measuretype;
        this.measureData = measureData;
    }
}
