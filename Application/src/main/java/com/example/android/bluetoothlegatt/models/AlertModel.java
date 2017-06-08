package com.example.android.bluetoothlegatt.models;

import java.io.Serializable;

public class AlertModel implements Serializable {
    public int hour;
    public int identifier;
    public int minute;
    public String mode;
    public int repeat;
    public boolean status;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getRepeat() {
        return this.repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String toString() {
        return "AlertModel{status=" + this.status + ", hour=" + this.hour + ", minute=" + this.minute + ", repeat=" + this.repeat + ", identifier=" + this.identifier + ", mode='" + this.mode + '\'' + '}';
    }
}
