/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.bluetoothlegatt;

import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class SampleGattAttributes {
    public static String S1 = "00001800-0000-1000-8000-00805f9b34fb";
    public static String C1 = "00002a00-0000-1000-8000-00805f9b34fb";
    public static String C2 = "00002a01-0000-1000-8000-00805f9b34fb";
    public static String C3 = "00002a04-0000-1000-8000-00805f9b34fb";

    public static String S2 = "00001801-0000-1000-8000-00805f9b34fb";
    public static String C4 = "00002a05-0000-1000-8000-00805f9b34fb";

    // Custom Services
    public static String S3 = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
    public static String C5 = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
    public static String C6 = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";

    public static String S4 = "00001530-1212-efde-1523-785feabcd123";
    public static String C7 = "00001532-1212-efde-1523-785feabcd123";
    public static String C8 = "00001531-1212-efde-1523-785feabcd123";
    public static String C9 = "00001534-1212-efde-1523-785feabcd123";

    public static String S5 = "0000fee7-0000-1000-8000-00805f9b34fb";
    public static String C10 = "0000fec9-0000-1000-8000-00805f9b34fb";
    public static String C11 = "0000fea1-0000-1000-8000-00805f9b34fb";
    public static String C12 = "0000fea2-0000-1000-8000-00805f9b34fb";


    private static HashMap<String, String> attributes = new HashMap();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
//    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";


    //ECG and PW Service
//    public static String SERVICE1 = "0aabcdef-1111-2222-0000-facebeadaaaa";
//    public static String SERVICE2 = "1aabcdef-1111-2222-0000-facebeadaaaa";
//    public static String SERVICE3 = "2aabcdef-1111-2222-0000-facebeadaaaa";
//    public static String SERVICE4 = "eca95120-f940-11e4-9ed0-0002a5d5c51b";
//    public static String SERVICE5 = "00001800-0000-1000-8000-00805f9b34fb";
//    public static String SERVICE6 = "00001801-0000-1000-8000-00805f9b34fb";

    //Characteristics Service One
//    public static String CHAR1 = "facebead-ffff-eeee-0001-facebeadaaaa";
    //Heart Rate, Blood Pressure
//    public static String CHAR2 = "facebead-ffff-eeee-0002-facebeadaaaa";
//    public static String CHAR3 = "facebead-ffff-eeee-0003-facebeadaaaa";
    //ECG
//    public static String CHAR4 = "facebead-ffff-eeee-0004-facebeadaaaa";
    //PW
//    public static String CHAR5 = "facebead-ffff-eeee-0005-facebeadaaaa";

    //Characteristics Service Two
//    public static String CHAR6 = "facebead-ffff-eeee-0010-facebeadaaaa";
//    public static String CHAR7 = "facebead-ffff-eeee-0020-facebeadaaaa";

    //Characteristics Service Three
//    public static String CHAR8 = "facebead-ffff-eeee-0100-facebeadaaaa";
//    public static String CHAR9 = "facebead-ffff-eeee-0200-facebeadaaaa";

    //Characteristics Service Four
//    public static String CHAR10 = "c1c8a4a0-f941-11e4-a534-0002a5d5c51b";

    //Characteristics Service Five
//    public static String CHAR11 = "00002a00-0000-1000-8000-00805f9b34fb";
//    public static String CHAR12 = "00002a01-0000-1000-8000-00805f9b34fb";

    //Characteristics Service Six
//    public static String CHAR13 = "00002a05-0000-1000-8000-00805f9b34fb";

    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9";

    static {
        // Sample Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");

        attributes.put(S1, "SERVICE 1");
        attributes.put(S2, "SERVICE 2");
        attributes.put(S3, "SERVICE 3");
        attributes.put(S4, "SERVICE 4");
        attributes.put(S5, "SERVICE 5");

        // Sample Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");

        attributes.put(C1, "Characteristics 1");
        attributes.put(C2, "Characteristics 2");
        attributes.put(C3, "Characteristics 3");
        attributes.put(C4, "Characteristics 4");
        attributes.put(C5, "Characteristics 5");

        attributes.put(C6, "Characteristics 6");
        attributes.put(C7, "Characteristics 7");

        attributes.put(C8, "Characteristics 8");
        attributes.put(C9, "Characteristics 9");

        attributes.put(C10, "Characteristics 10");

        attributes.put(C11, "Characteristics 11");
        attributes.put(C12, "Characteristics 12");

        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
