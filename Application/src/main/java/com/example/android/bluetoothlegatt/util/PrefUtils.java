package com.example.android.bluetoothlegatt.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    public static final String PREF_NAME = "UserData_Alex";
    public static SharedPreferences sp;

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue) {
        sp = ctx.getSharedPreferences(PREF_NAME, 0);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        sp = ctx.getSharedPreferences(PREF_NAME, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        if (ctx != null) {
            sp = ctx.getSharedPreferences(PREF_NAME, 0);
        }
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context ctx, String key, String value) {
        sp = ctx.getSharedPreferences(PREF_NAME, 0);
        sp.edit().putString(key, value).commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        if (context != null) {
            sp = context.getSharedPreferences(PREF_NAME, 0);
        }
        return sp.getInt(key, defaultValue);
    }

    public static void setInt(Context context, String key, int value) {
        if (context != null) {
            sp = context.getSharedPreferences(PREF_NAME, 0);
            sp.edit().putInt(key, value).commit();
        }
    }

    public static long getLong(Context context, String key, long defaultValue) {
        if (context != null) {
            sp = context.getSharedPreferences(PREF_NAME, 0);
        }
        return sp.getLong(key, defaultValue);
    }

    public static void setLong(Context context, String key, long value) {
        if (context != null) {
            sp = context.getSharedPreferences(PREF_NAME, 0);
            sp.edit().putLong(key, value).commit();
        }
    }

    public static void removeAllSp(Context ctx) {
        sp = ctx.getSharedPreferences(PREF_NAME, 0);
        sp.edit().clear().commit();
    }
}
