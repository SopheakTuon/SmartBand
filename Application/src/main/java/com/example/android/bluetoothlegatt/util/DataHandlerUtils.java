package com.example.android.bluetoothlegatt.util;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

import com.example.android.bluetoothlegatt.ble_service.BleCommand;
import com.example.android.bluetoothlegatt.constant.Constants;

public class DataHandlerUtils {
    public static byte[] stringTo16Byte(String temp) {
        int len = temp.length();
        for (int i = 0; i < 16 - len && temp.length() != 16; i++) {
            temp = temp + Constants.VIA_RESULT_SUCCESS;
        }
        return temp.getBytes();
    }

    public static final short getShort(byte[] buf, boolean asc, int len) {
        short r = (short) 0;
        int i;
        if (asc) {
            for (i = len - 1; i >= 0; i--) {
                r = (short) ((buf[i] & 255) | ((short) (r << 8)));
            }
        } else {
            for (i = 0; i < len; i++) {
                r = (short) ((buf[i] & 255) | ((short) (r << 8)));
            }
        }
        return r;
    }

    public static int stringToByte(String in, byte[] b) throws Exception {
        if (b.length < in.length() / 2) {
            throw new Exception("byte array too small");
        }
        int j = 0;
        StringBuffer buf = new StringBuffer(2);
        int i = 0;
        while (i < in.length()) {
            buf.insert(0, in.charAt(i));
            buf.insert(1, in.charAt(i + 1));
            int t = Integer.parseInt(buf.toString(), 16);
            System.out.println("byte hex value:" + t);
            b[j] = (byte) t;
            i++;
            buf.delete(0, 2);
            i++;
            j++;
        }
        return j;
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
                    r = (r << 8) | (buf[i] & 255);
                }
            } else {
                for (i = 0; i < len; i++) {
                    r = (r << 8) | (buf[i] & 255);
                }
            }
            return r;
        }
    }

    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - (i * 8)));
        }
        return b;
    }

    public static byte[] shortToBytes(short num) {
        byte[] b = new byte[2];
        for (int i = 0; i < 2; i++) {
            b[i] = (byte) (num >>> (i * 8));
        }
        return b;
    }

    private static char findHex(byte b) {
        int t = new Byte(b).intValue();
        if (t < 0) {
            t += 16;
        }
        if (t < 0 || t > 9) {
            return (char) ((t - 10) + 65);
        }
        return (char) (t + 48);
    }

    public static String byteToString(byte b) {
        byte high = (byte) ((b & (byte) -16) >> 4);
        byte low = (byte) (b & BleCommand.RequestType.GET_SENSOR_VERSION);
        StringBuffer buf = new StringBuffer();
        buf.append(findHex(high));
        buf.append(findHex(low));
        return buf.toString();
    }

    public static final byte[] getBytes(short s, boolean asc) {
        byte[] buf = new byte[2];
        int i;
        if (asc) {
            for (i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 255);
                s = (short) (s >> 8);
            }
        } else {
            for (i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 255);
                s = (short) (s >> 8);
            }
        }
        return buf;
    }

    public static final byte[] getBytes(int s, boolean asc) {
        byte[] buf = new byte[4];
        int i;
        if (asc) {
            for (i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 255);
                s >>= 8;
            }
        } else {
            for (i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 255);
                s >>= 8;
            }
        }
        return buf;
    }

    public static final byte[] getBytes(long s, boolean asc) {
        byte[] buf = new byte[8];
        int i;
        if (asc) {
            for (i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) ((int) (s & 255));
                s >>= 8;
            }
        } else {
            for (i = 0; i < buf.length; i++) {
                buf[i] = (byte) ((int) (s & 255));
                s >>= 8;
            }
        }
        return buf;
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
                    r = (r << 8) | (buf[i] & 255);
                }
            } else {
                for (byte b : buf) {
                    r = (r << 8) | (b & 255);
                }
            }
            return r;
        }
    }

    public static final long getLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        } else {
            long r = 0;
            int i;
            if (asc) {
                for (i = buf.length - 1; i >= 0; i--) {
                    r = (r << 8) | ((long) (buf[i] & 255));
                }
            } else {
                for (byte b : buf) {
                    r = (r << 8) | ((long) (b & 255));
                }
            }
            return r;
        }
    }

    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[(hexstr.length() / 2)];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            int j2 = j + 1;
            char c0 = hexstr.charAt(j);
            j = j2 + 1;
            b[i] = (byte) ((parse(c0) << 4) | parse(hexstr.charAt(j2)));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return ((c - 97) + 10) & 15;
        }
        if (c >= 'A') {
            return ((c - 65) + 10) & 15;
        }
        return (c - 48) & 15;
    }

    public static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 255).byteValue();
            temp >>= 8;
        }
        b[0] = (byte) (b[0] ^ b[1]);
        b[1] = (byte) (b[1] ^ b[0]);
        b[0] = (byte) (b[0] ^ b[1]);
        return b;
    }

    public static String byte2hexStr(byte[] buffer) {
        String h = "1.0";
        for (byte b : buffer) {
            String temp = Integer.toHexString(b & 255);
            if (temp.length() == 1) {
                temp = Constants.VIA_RESULT_SUCCESS + temp;
            }
            h = h + " " + temp;
        }
        return h;
    }

    public static int combineBytehex_int(byte data) {
        return Integer.parseInt(Integer.toHexString(data).substring(6), 16);
    }

    public static long combineByte_long(byte b1, byte b2, byte b3, byte b4) {
        int t3 = (b3 << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        return ((long) (((((b1 << 24) & ViewCompat.MEASURED_STATE_MASK) | ((b2 << 16) & 16711680)) | t3) | (b4 & 255))) & 4294967295L;
    }
}
