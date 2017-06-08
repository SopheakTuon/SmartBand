package com.example.android.bluetoothlegatt.models;

import android.content.Context;

public class DisturbanceModel {
    public int endHour;
    public int endMinute;
    public int isOn;
    public int startHour;
    public int startMinute;

    public DisturbanceModel(Context context) {
//        if (SPUtils.getBoolean(context, SPUtils.NOOBSTRUCT_NOTI_SWITCH, false)) {
//            this.isOn = 1;
//        } else {
//            this.isOn = 0;
//        }
//        String startTime = SPUtils.getString(context, SPUtils.NOOBSTRUCT_START, BuildConfig.VERSION_NAME).trim();
//        if (startTime != BuildConfig.VERSION_NAME) {
//            this.startHour = Integer.valueOf(startTime.substring(0, 2)).intValue();
//            this.startMinute = Integer.valueOf(startTime.substring(3)).intValue();
//        }
//        String endTime = SPUtils.getString(context, SPUtils.NOOBSTRUCT_END, BuildConfig.VERSION_NAME).trim();
//        if (endTime == BuildConfig.VERSION_NAME) {
//            return;
//        }
//        if (endTime.length() > 5) {
//            this.endHour = Integer.valueOf(endTime.substring(2, 4)).intValue();
//            this.endMinute = Integer.valueOf(endTime.substring(5)).intValue();
//            return;
//        }
//        this.endHour = Integer.valueOf(endTime.substring(0, 2)).intValue();
//        this.endMinute = Integer.valueOf(endTime.substring(3)).intValue();
    }
}
