package com.example.android.bluetoothlegatt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static String offSetTimeZone() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        String displayName = timeZone.getDisplayName();
        int offset = timeZone.getOffset(System.currentTimeMillis());
        int offsetHour = offset / 3600000;
        return String.valueOf(offset);
    }

    public static String offSetSummerTimeZone() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DST_OFFSET));
    }

    public static long String2Stamp(String time) {
        long times = 0;
        try {
            times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times / 1000;
    }

    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long lcc = Long.valueOf(time).longValue();
        return sdr.format(new Date(((long) Integer.parseInt(time)) * 1000));
    }

    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 7;
        }
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }
}
