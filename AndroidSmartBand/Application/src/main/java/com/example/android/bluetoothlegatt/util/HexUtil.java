package com.example.android.bluetoothlegatt.util;

import android.support.v4.view.MotionEventCompat;

public class HexUtil {
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            String hv = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            str = new StringBuilder(String.valueOf(str)).append(Integer.toHexString(s.charAt(i))).toString();
        }
        return "0x" + str;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) ((charToByte(hexChars[pos]) << 4) | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] intToBytes(int value) {
        return new byte[]{(byte) ((value >> 24) & MotionEventCompat.ACTION_MASK), (byte) ((value >> 16) & MotionEventCompat.ACTION_MASK), (byte) ((value >> 8) & MotionEventCompat.ACTION_MASK), (byte) (value & MotionEventCompat.ACTION_MASK)};
    }

    public static int bytesToInt(byte[] src, int offset) {
        return (((src[offset] & MotionEventCompat.ACTION_MASK) | ((src[offset + 1] & MotionEventCompat.ACTION_MASK) << 8)) | ((src[offset + 2] & MotionEventCompat.ACTION_MASK) << 16)) | ((src[offset + 3] & MotionEventCompat.ACTION_MASK) << 24);
    }

    public static final int getInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        } else {
            int r = 0;
            int i;
            if (asc) {
                for (i = buf.length - 1; i >= 0; i--) {
                    r = (r << 8) | (buf[i] & MotionEventCompat.ACTION_MASK);
                }
            } else {
                for (byte b : buf) {
                    r = (r << 8) | (b & MotionEventCompat.ACTION_MASK);
                }
            }
            return r;
        }
    }

    public static final int getInt(byte[] buf, boolean asc, int len) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (len > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        } else {
            int r = 0;
            int i;
            if (asc) {
                for (i = len - 1; i >= 0; i--) {
                    r = (r << 8) | (buf[i] & MotionEventCompat.ACTION_MASK);
                }
            } else {
                for (i = 0; i < len; i++) {
                    r = (r << 8) | (buf[i] & MotionEventCompat.ACTION_MASK);
                }
            }
            return r;
        }
    }
}
