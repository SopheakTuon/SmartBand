/*******************************************************************
 * @file MainMenu.java
 * @par Copyright (C) 2014-2015 MegaChips Corporation
 * @date 2014/02/26
 *******************************************************************/

package com.weike.chiginon;

public class BroadcastCommand {

    // BroadcastCommand type
    public static final int BLE_STATUS_CHECK = 0;
    public static final int BLE_STATUS_RESULT = 1;
    public static final int BLE_SEND_DATA = 2;
    public static final int BLE_RECEIVE_DATA = 10;
    public static final int CALIBRATION_END = 3;
    public static final int STATE_STARTED = 4;
    public static final int STATE_STOPPED = 5;
    public static final int ERASE_END = 6;
    public static final int LOGSEND_END = 7;
    public static final int SETITNG_DATA = 8;
    public static final int LOG_DETAIL = 9;
    public static final int CONNECT_TYPE = 127;


    public static final int RESPONSE_GEOFENCING_PARAMETER_SUCCESS = 8;
    public static final int RESPONSE_GEOFENCING_PARAMETER_FAIL = 9;
    public static final int RESPONSE_GEOFENCING_RESET_SUCCESS = 10;
    public static final int RESPONSE_GEOFENCING_RESET_FAIL = 11;
    public static final int SWING_CALI_END = 12;
    public static final int RESULT_ACK = 13;
    public static final int RESULT_NAK = 14;
    public static final int SEND_FW_OK = 15;
    public static final int SEND_FW_NG = 16;

    // BroadcastCommand action
    public final static String ACTION_GATT_CONNECTED = "co.megachps.chignonmonitor.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED = "co.megachps.chignonmonitor.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "co.megachps.chignonmonitor.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE = "co.megachps.chignonmonitor.ACTION_DATA_AVAILABLE";
    public final static String ACTION_BLE_STATUS_RESULT = "co.megachps.chignonmonitor.ACTION_BLE_STATUS_RESULT";
    public final static String ACTION_BLE_SEND_REQUEST_DENIED = "co.megachps.chignonmonitor.ACTION_BLE_SEND_REQUEST_DENIED";
    public final static String DATA_RECEIVED_FROM_ACTIVITY = "co.megachps.chignonmonitor.DATA_RECEIVED_FROM_ACTIVITY";
    public final static String DEVICE_DOES_NOT_SUPPORT_UART = "co.megachps.chignonmonitor.DEVICE_DOES_NOT_SUPPORT_UART";
    public final static String EXTRA_DATA = "co.megachps.chignonmonitor.EXTRA_DATA";

    public class PDRType {
        public static final int PDR_MODE = 0;
        public static final int PDR_MODE_FIX = 1;
        public static final int PDR_MODE_SWING = 2;
        public static final int PDR_MODE_WRIST = 3;
        public static final int PDR_MODE_AUTO = 4;
        public static final int PDR_MODE_WATCHING = 5;
    }

    public class SwingType {
        public static final int LINE_MODE = 0;
        public static final int DIAGONAL_LINE_MODE = 1;
    }
}


