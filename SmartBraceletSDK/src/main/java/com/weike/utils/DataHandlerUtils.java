package com.weike.utils;

public class DataHandlerUtils {
    /*
     * 字符串转byte[] 这个方法转换后的结果是会多一些 48字符进来的就是代表的是0不知道为什么，但是可以只是取出指定的字符串就行了
     */
    public static byte[] stringTo16Byte(String temp) {

        int len = temp.length();
        for (int i = 0; i < 16 - len; i++) {
            if (temp.length() == 16) {
                break;
            }
            temp = temp + "0";

        }
        return temp.getBytes();
    }

    /* byte转short */
    public final static short getShort(byte[] buf, boolean asc, int len) {
        short r = 0;
        if (asc)
            for (int i = len - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        else
            for (int i = 0; i < len; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }

        return r;
    }

    /* B2 -> 0xB2 */
    public static int stringToByte(String in, byte[] b) throws Exception {
        if (b.length < in.length() / 2) {
            throw new Exception("byte array too small");
        }

        int j = 0;
        StringBuffer buf = new StringBuffer(2);
        for (int i = 0; i < in.length(); i++, j++) {
            buf.insert(0, in.charAt(i));
            buf.insert(1, in.charAt(i + 1));
            int t = Integer.parseInt(buf.toString(), 16);
            System.out.println("byte hex value:" + t);
            b[j] = (byte) t;
            i++;
            buf.delete(0, 2);
        }

        return j;
    }

    /* byte to int */
    public final static int getInt(byte[] buf, boolean asc, int len) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (len > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = len - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < len; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    /* int -> byte[] */
    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }

        return b;
    }

    /* short to byte[] */
    public static byte[] shortToBytes(short num) {
        byte[] b = new byte[2];

        for (int i = 0; i < 2; i++) {
            b[i] = (byte) (num >>> (i * 8));
        }

        return b;
    }

    /* byte to String */
    private static char findHex(byte b) {
        int t = new Byte(b).intValue();
        t = t < 0 ? t + 16 : t;

        if ((0 <= t) && (t <= 9)) {
            return (char) (t + '0');
        }

        return (char) (t - 10 + 'A');
    }

    public static String byteToString(byte b) {
        byte high, low;
        byte maskHigh = (byte) 0xf0;
        byte maskLow = 0x0f;

        high = (byte) ((b & maskHigh) >> 4);
        low = (byte) (b & maskLow);

        StringBuffer buf = new StringBuffer();
        buf.append(findHex(high));
        buf.append(findHex(low));

        return buf.toString();
    }

    /* short -> byte */
    public final static byte[] getBytes(short s, boolean asc) {
        byte[] buf = new byte[2];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        return buf;
    }

    /* int -> byte[] */
    public final static byte[] getBytes(int s, boolean asc) {
        byte[] buf = new byte[4];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        return buf;
    }

    /* long -> byte[] */
    public final static byte[] getBytes(long s, boolean asc) {
        byte[] buf = new byte[8];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        return buf;
    }

    /* byte[]->int */
    public final static int getInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    /* byte[] -> long */
    public final static long getLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
        long r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        return r;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    public static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();
            temp = temp >> 8;
        }
        b[0] = (byte) (b[0] ^ b[1]);
        b[1] = (byte) (b[1] ^ b[0]);
        b[0] = (byte) (b[0] ^ b[1]);
        return b;
    }

    /**
     * byte数组转换成十六进制字符串
     *
     * @param buffer
     * @return
     */
    public static String byte2hexStr(byte[] buffer) {
        String h = "";
        for (int i = 0; i < buffer.length; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h += " " + temp;//ab 00 0b ff 52 80 10 0b 19 03 3a 01 00 0d
        }
        return h;
    }

    /**
     * byte转16进制int
     *
     * @param data
     * @return
     */
    public static int combineBytehex_int(byte data) {
        String hex_data;
        int int_data;
        hex_data = Integer.toHexString(data);
        hex_data = hex_data.substring(6);
        int_data = Integer.parseInt(hex_data, 16);
        return int_data;
    }

    public static long combineByte_long(byte b1, byte b2, byte b3, byte b4) {
        int t1 = ((int) b1 << 24) & 0xFF000000;
        int t2 = ((int) b2 << 16) & 0x00FF0000;
        int t3 = ((int) b3 << 8) & 0x0000FF00;
        int t4 = ((int) b4) & 0x000000FF;
        long temp = (t1 | t2 | t3 | t4) & (0xFFFFFFFFL);
        return temp;
    }

    /**
     * 拼接字节数组
     * @param data1
     * @param data2
     * @return
     */
    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }
}