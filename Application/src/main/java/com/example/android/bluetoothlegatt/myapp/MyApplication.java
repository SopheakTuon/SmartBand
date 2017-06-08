package com.example.android.bluetoothlegatt.myapp;

import android.app.Application;

/**
 * @author Sopheak Tuon
 * @created on 24-Feb-17
 */

public class MyApplication extends Application {
    private static final String TAG;


    static {
        TAG = MyApplication.class.getSimpleName();
    }

    public void onCreate() {
        super.onCreate();
    }


}
