package com.example.android.bluetoothlegatt.constant;

import com.example.android.bluetoothlegatt.models.MainDataList;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String ACTION_DEVICE_FIRM_VERSION = "ACTION_DEVICE_FIRM_VERSION";
    public static final String ACTION_GATT_CONNING = "ACTION_GATT_CONNING";
    public static final String ACTION_GATT_DEVICE_BIND_REQUEST = "ACTION_GATT_DEVICE_BIND_REQUEST";
    public static final String ACTION_GATT_DEVICE_MATCH_ACK = "ACTION_GATT_DEVICE_MATCH_ACK";
    public static final String ACTION_GATT_DEVICE_UNBIND_ACK = "ACTION_GATT_DEVICE_UNBIND_ACK";
    public static final String ACTION_GATT_SEARCH = "ACTION_GATT_SEARCH";
    public static final String ACTION_GATT_SOS = "GATT_SOS";
    public static final String ACTION_GATT_SUCCESS_CONN = "ACTION_GATTS_CONN_SUCCES";
    public static final String ACTION_MAIN_DATA_ECG_ALL_DATA = "ACTION_MAIN_DATA_ECG_ALL_DATA";
    public static final String ACTION_MAIN_DATA_FIRM_FAULT = "FIRM_FAULT";
    public static final String ACTION_MAIN_DATA_FIRM_SUCCESS = "FIRM_SUCCESS";
    public static final String ACTION_SERVICE_GATT_CONNECTED = "ACTION_GATT_CONNECTED";
    public static final String ACTION_SERVICE_GATT_DISCONNECTED = "ACTION_GATT_DISCONNECTED";
    public static final String ACTION_SERVICE_GATT_DISCOVERED = "ACTION_GATT_SERVICES_DISCOVERED";
    public static final String ACTION_UP_FIRMWARE_COMPLETE = "ACTION_UP_FIRMWARE_COMPLETE";
    public static final String BLE_BOND_REQUEST = "BLE_BIND_REQUEST";
    public static final String BLE_CONNECTING = "BLE_CONNECTING";
    public static final String BLE_HEART_PACKAGE = "BLE_HEART_PACKAGE";
    public static final String BLE_MATCH = "BLE_MATCHACK";
    public static final String BLE_ON_RESTART = "BLE_ON_RESTART";
    public static final String BLE_OPEN = "BLE_OPEN";
    public static final String BLE_RESEARCH = "BLE_RESEARCH";
    public static final String BLE_SCAN_STOP = "BLE_SCAN_STOP";
    public static final String BLE_SEARCH_BACK = "BLE_SEARCH_BACK";
    public static final String BLE_SEARCH_PRE = "BLE_SEARCH_PRE";
    public static final String BLE_SERVICE = "BLE_SERVICE";
    public static final String BLE_SOS = "BLE_SOS";
    public static final String BLUETOOTH_ACTION = "CONNECTION_STATE_CHANGED";
    public static final String BLUETOOTH_STATE_CHANGED = "STATE_CHANGED";
    public static final String CHANGE_BATTERY_STATUS = "CHANGE_BATTERY_STATUS";
    public static final String CLOSE_FIRMWARE_UPING_DIALOG = "CLOSE_FIRMWARE_UPING_DIALOG";
    public static final String CLOSE_SEARCH_DIALOG = "CLOSE_SEARCH_DIALOG";
    public static final String CLOSE_UP_FIRMWARE_DIALOG = "CLOSE_UP_FIRMWARE_DIALOG";
    public static final int DELAY_MILLIS_UPDATE_DEVICE_TIME = 200;
    public static final int DELAY_TICKTOCK_FIRMWARE_UPDATE = 1000;
    public static final int DELAY_TIME_LINKING_BLE = 10000;
    public static int DELAY_TIME_MATCHING_BLE = 0;
    public static final String DEVICE_CONN = "DEVICE_CONN";
    public static final String DEVICE_DISCONNECT = "DEVICE_DISCONNECT";
    public static final String DEVICE_NAME = "Helo";
    public static final String DEVICE_SUB_NAME1 = "seedmorn";
    public static final String DEVICE_SUB_NAME2 = "HeloHL01";
    public static final String DEVICE_TARGET_MAC = "Target";
    public static final String FIRMWARE_UPDATE_FAILED = "FIRMWARE_UPDATE_FAILED";
    public static final String FIRMWARE_UPDATE_SUCESS = "FIRMWARE_UPDATE_SUCESS";
    public static final String FIRMWARE_UPING_DIALOG = "FIRMWARE_UPING_DIALOG";
    public static final String FIRMWARE_UPING_PROGRESSBAR = "FIRMWARE_UPING_PROGRESSBAR";
    public static ArrayList<String> GlobalData_datedata = null;
    public static List<MainDataList> GlobalData_list = null;
    public static boolean INIT_AT_BOOT = false;
    public static final int NOTIFICATION_FLAG_NEW_APP = 4;
    public static final int NOTIFICATION_FLAG_NEW_FW = 5;
    public static final long PERIOD_HEART_PACKAGE_SUCCESS = 30000;
    public static final long PERIOD_SCAN = 8000;
    public static int POWER_BATTERY = 0;
    public static final String SHARED_PREFRENCE_DECRY_TOKEN = "SHARED_PREFRENCE_DECRY_TOKEN";
    public static final String SHOW_BIND_DIALOG = "SHOW_BIND_DIALOG";
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNED = 0;
    public static final int STATE_SCANNING = 3;
    public static final boolean SWITCHBIND = false;
    public static int VERSION_APP;
    public static int VERSION_FIRM;
    public static int childPosition;
    public static int groupPosition;
    public static int heartFail;
    public static boolean isBLeEnabled;
    public static boolean isCouldUpFirmware;
    public static boolean isEnabled;
    public static boolean isFirmWorkVersionInfo;
    public static boolean isFirst;
    public static boolean isInitBle;
    public static boolean isLoad;
    public static boolean isMain;
    public static boolean isMainFragment;
    public static boolean isMatchInfo;
    public static boolean isMenu;
    public static boolean isNewAppOnServer_OnPause;
    public static boolean isNewFirmwareOnServer_OnPause;
    public static boolean isOnBondRequest_OnPause;
    public static boolean isOnFirmwareUpdating;
    public static boolean isOnPause;
    public static boolean isReconnect;
    public static boolean isRequest;
    public static boolean isSos;
    public static boolean isSysblestatus;
    public static final int[] low_batery;
    public static String message_NewAppOnServer;
    public static String message_NewFirmwareOnServer;
    public static int notification_count_lowbetery;
    public static int notification_count_measures;
    public static int notification_count_newapp;
    public static int notification_count_newfw;
    public static int notification_count_sleep;
    public static int notification_count_sos;
    public static int notification_count_steps;
    public static boolean status_ConnInit;
    public static boolean status_Connected;
    public static boolean status_Connecting;
    public static boolean status_Scanning;
    public static boolean status_matching_or_binding;

    public static final int[] version_ForceUpdate;

    static {
        notification_count_sleep = STATE_DISCONNED;
        notification_count_steps = STATE_DISCONNED;
        notification_count_sos = STATE_DISCONNED;
        notification_count_lowbetery = STATE_DISCONNED;
        notification_count_newapp = STATE_DISCONNED;
        notification_count_newfw = STATE_DISCONNED;
        notification_count_measures = STATE_DISCONNED;
        INIT_AT_BOOT = SWITCHBIND;
        version_ForceUpdate = new int[]{STATE_CONNECTING, STATE_CONNECTED, STATE_SCANNING, NOTIFICATION_FLAG_NEW_APP, NOTIFICATION_FLAG_NEW_FW};
        low_batery = new int[]{30, 15, 10, NOTIFICATION_FLAG_NEW_FW};
        DELAY_TIME_MATCHING_BLE = DELAY_TICKTOCK_FIRMWARE_UPDATE;
        POWER_BATTERY = STATE_DISCONNED;
        isOnFirmwareUpdating = SWITCHBIND;
        isCouldUpFirmware = SWITCHBIND;
        VERSION_FIRM = STATE_DISCONNED;
        VERSION_APP = STATE_DISCONNED;
        groupPosition = STATE_DISCONNED;
        childPosition = STATE_DISCONNED;
        isMain = true;
        isMenu = true;
        isFirst = true;
        status_Scanning = SWITCHBIND;
        status_ConnInit = SWITCHBIND;
        status_Connecting = SWITCHBIND;
        status_matching_or_binding = SWITCHBIND;
        status_Connected = SWITCHBIND;
        isReconnect = SWITCHBIND;
        isInitBle = SWITCHBIND;
        isOnPause = SWITCHBIND;
        isOnBondRequest_OnPause = SWITCHBIND;
        isNewAppOnServer_OnPause = SWITCHBIND;
        isNewFirmwareOnServer_OnPause = SWITCHBIND;
        message_NewFirmwareOnServer = "";
        message_NewAppOnServer = "";
        isSysblestatus = true;
        isRequest = SWITCHBIND;
        isMainFragment = SWITCHBIND;
        isEnabled = SWITCHBIND;
        isBLeEnabled = SWITCHBIND;
        isSos = SWITCHBIND;
        isLoad = SWITCHBIND;
        TOP_MAC = "";
        heartFail = STATE_DISCONNED;
        GlobalData_list = null;
        GlobalData_datedata = null;
        isMatchInfo = SWITCHBIND;
        isFirmWorkVersionInfo = SWITCHBIND;
    }

    public static final String ACTION_GATT_BLOOD_PRESSURE_NOISE = "ACTION_GATT_BLOOD_PRESSURE_NOISE";

    public static final String ACTION_GATT_LOAD_DATA = "ACTION_GATT_LOAD_DATA";
    public static final String ACTION_GATT_LOAD_DATA_SLEEP = "ACTION_GATT_LOAD_DATA_SLEEP";

    public static final String ACTION_GATT_SLEEP_NEW = "ACTION_GATT_SLEEP_NEW";

    public static final String ACTION_MAIN_DATA_BATTERY_POWER = "BUTTERY_POWER";
    public static final String ACTION_MAIN_DATA_BP = "BluetoothLeService.DATA_BLOOD_PRESSURE";
    public static final String ACTION_MAIN_DATA_BREATH = "DATA_BREATH_RATE";
    public static final String ACTION_MAIN_DATA_ECG = "DATA_ECG";

    public static final String ACTION_MAIN_DATA_FATIGUE = "DATA_FATIGUE";

    public static final String ACTION_MAIN_DATA_HR = "DATA_HEART_RATE";
    public static final String ACTION_MAIN_DATA_KLL = "DATA_KLL";
    public static final String ACTION_MAIN_DATA_MOOD = "DATA_MOOD";
    public static final String ACTION_MAIN_DATA_PW = "ACTION_MAIN_DATA_PW";
    public static final String ACTION_MAIN_DATA_SLEEP = "DATA_SLEEP";
    public static final String ACTION_MAIN_DATA_STEPS = "ACTION_MAIN_DATA_STEPS";
    public static final String ACTION_MATCH_INFO_TO_DEVICE = "ACTION_MATCH_INFO_TO_DEVICE";


    public static final String LEDCONTORLLSUCCESS = "LED_CONTORLL_SUCCESS";


    public static String TOP_MAC = null;


    /**
     *Data direction: BLE device -> App
     */
    public final class CMD {
        //BIND COMMAND
        public static final String START_SYCHRONIZATION = "21";
        public static final String STOP_SYCHRONIZATIO = "22";
        public static final String DEVICE_BIND_REQUEST = "23";

        //DATA COMMAND
        public static final String GATT_SOS = "24";
        public static final String DATA_STEP_COUNT = "31";
        public static final String DATA_HEART_RATE = "32";
        public static final String DATA_PPG = "33";
        public static final String DATA_KLL = "34";
        public static final String DATA_SLEEP = "35";
        public static final String DEVICE_MATCH_ACK = "37";
        public static final String DEVICE_UNBIND_ACK = "38";
        public static final String DATA_MOOD = "3B";
        public static final String DATA_FATIGUE = "3C";
        public static final String DATA_BREATH_RATE = "3D";
        public static final String HEALTH_PLAN = "3E";
        public static final String CRC_DATA_SYNCHRONIZATION = "3F";

        public static final String DATA_BLOOD_PRESSURE = "41";
        public static final String DATA_ECG = "42";
        public static final String DATA_BATTERY_POWER = "43";
        public static final String SLEEP_DATA = "44";


    }

    /**
     * Data direction: APP -> BLE Device
     */
    public static final class WriteCommandCode{
        public static final byte STEP_COUNT = 0x01;
        public static final byte HEART_RATE = 0x02;
        public static final byte PPG = 0x03;
        public static final byte CALORY_DATA = 0x04;
        public static final byte SLEEP_DATA = 0x05;
        public static final byte MOOD_FATIGUE = 0x06;
        public static final byte FATIGUE = 0x07;
        public static final byte CLEAR_DISTANCE = 0x08;
        public static final byte REQUEST_UNBIND = 0x09;
        public static final byte ECG = 0x0A;
        public static final byte BREATH_RATE = 0x0B;
        public static final byte BLOOD_PRESSURE = 0x0C;
        public static final byte TURN_OFF_DEVICE = 0x0E;
        public static final byte STOP_CURRENT_MEASUREMENT = 0x0F;
        public static final byte PAIR_INFORMATION = 0x11;
        public static final byte DATE_TIME_SYNCHRONIZATION = 0x12;
        public static final byte REQUEST_TO_BINDING = 0x13;
        public static final byte TIMER = 0x14;
        public static final byte HEALTH_PLAN = 0x17;
        public static final byte CALIBRATION_BLOOD_PRESSURE = 0x19;
        public static final byte SECOND_MATCH = 0x1B;
        public static final byte START_DATA_SYNCHRONIZATION = 0x21;
        public static final byte END_DATA_SYNCHRONIZATION = 0x22;
        public static final byte REQUEST_TO_BIND = 0x23;
        public static final byte SOS = 0x24;
    }


    public final class Smile {
//        public static final byte BYTE_MARKER_END_OF_CONTENT = (byte) -1;
//        public static final byte BYTE_MARKER_END_OF_STRING = (byte) -4;
//        public static final int HEADER_BIT_HAS_RAW_BINARY = 4;
//        public static final int HEADER_BIT_HAS_SHARED_NAMES = 1;
//        public static final int HEADER_BIT_HAS_SHARED_STRING_VALUES = 2;
//        public static final byte HEADER_BYTE_1 = (byte) 58;
//        public static final byte HEADER_BYTE_2 = (byte) 41;
//        public static final byte HEADER_BYTE_3 = (byte) 10;
//        public static final byte HEADER_BYTE_4 = (byte) 0;
//        public static final int HEADER_VERSION_0 = 0;
//        public static final int INT_MARKER_END_OF_STRING = 252;
//        public static final int MAX_SHARED_NAMES = 1024;
//        public static final int MAX_SHARED_STRING_LENGTH_BYTES = 65;
//        public static final int MAX_SHARED_STRING_VALUES = 1024;
//        public static final int MAX_SHORT_NAME_ASCII_BYTES = 64;
//        public static final int MAX_SHORT_NAME_UNICODE_BYTES = 56;
//        public static final int MAX_SHORT_VALUE_STRING_BYTES = 64;
//        public static final int MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING = 196;
//        public static final byte TOKEN_KEY_EMPTY_STRING = (byte) 32;
        public static final byte TOKEN_KEY_LONG_STRING = (byte) 52;
        public static final byte TOKEN_LITERAL_EMPTY_STRING = (byte) 32;
        public static final byte TOKEN_LITERAL_END_ARRAY = (byte) -7;
        public static final byte TOKEN_LITERAL_END_OBJECT = (byte) -5;
        public static final byte TOKEN_LITERAL_FALSE = (byte) 34;
        public static final byte TOKEN_LITERAL_NULL = (byte) 33;
//        public static final byte TOKEN_LITERAL_START_ARRAY = (byte) -8;
//        public static final byte TOKEN_LITERAL_START_OBJECT = (byte) -6;
//        public static final byte TOKEN_LITERAL_TRUE = (byte) 35;
//        public static final int TOKEN_MISC_BINARY_7BIT = 232;
//        public static final int TOKEN_MISC_BINARY_RAW = 253;
//        public static final int TOKEN_MISC_FLOAT_32 = 0;
//        public static final int TOKEN_MISC_FLOAT_64 = 1;
//        public static final int TOKEN_MISC_FLOAT_BIG = 2;
//        public static final int TOKEN_MISC_FP = 40;
//        public static final int TOKEN_MISC_INTEGER = 36;
//        public static final int TOKEN_MISC_INTEGER_32 = 0;
//        public static final int TOKEN_MISC_INTEGER_64 = 1;
//        public static final int TOKEN_MISC_INTEGER_BIG = 2;
//        public static final int TOKEN_MISC_LONG_TEXT_ASCII = 224;
//        public static final int TOKEN_MISC_LONG_TEXT_UNICODE = 228;
//        public static final int TOKEN_MISC_SHARED_STRING_LONG = 236;
//        public static final int TOKEN_PREFIX_KEY_ASCII = 128;
//        public static final int TOKEN_PREFIX_KEY_SHARED_LONG = 48;
//        public static final int TOKEN_PREFIX_KEY_SHARED_SHORT = 64;
//        public static final int TOKEN_PREFIX_KEY_UNICODE = 192;
//        public static final int TOKEN_PREFIX_MISC_OTHER = 224;
//        public static final int TOKEN_PREFIX_SHARED_STRING_SHORT = 0;
//        public static final int TOKEN_PREFIX_SHORT_UNICODE = 160;
//        public static final int TOKEN_PREFIX_SMALL_ASCII = 96;
//        public static final int TOKEN_PREFIX_SMALL_INT = 192;
//        public static final int TOKEN_PREFIX_TINY_ASCII = 64;
//        public static final int TOKEN_PREFIX_TINY_UNICODE = 128;
    }



    //Wake UP

    public static final String CANCEL_URI = "auth://cancel";
    public static final String CLOSE_URI = "auth://close";
    public static final int CODE_REQUEST_MAX = 6656;
    public static final int CODE_REQUEST_MIN = 5656;
    public static final String DEFAULT_PF = "openmobile_android";
    public static final String DEFAULT_UIN = "1000";
    public static final String DOWNLOAD_URI = "download://";
    public static final int ERROR_CONNECTTIMEOUT = -7;
    public static final int ERROR_FILE_EXISTED = -11;
    public static final int ERROR_HTTPSTATUS_ERROR = -9;
    public static final int ERROR_IO = -2;
    public static final int ERROR_JSON = -4;
    public static final int ERROR_LOCATION_TIMEOUT = -13;
    public static final int ERROR_LOCATION_VERIFY_FAILED = -14;
    public static final int ERROR_NETWORK_UNAVAILABLE = -10;
    public static final int ERROR_NO_SDCARD = -12;
    public static final int ERROR_PARAM = -5;
    public static final int ERROR_QQVERSION_LOW = -15;
    public static final int ERROR_SOCKETTIMEOUT = -8;
    public static final int ERROR_UNKNOWN = -6;
    public static final int ERROR_URL = -3;
    public static final boolean FLAG_DEBUG = true;
    public static final String GRAPH_BASE = "https://openmobile.qq.com/";
    public static final String GRAPH_INTIMATE_FRIENDS = "friends/get_intimate_friends_weibo";
    public static final String GRAPH_NICK_TIPS = "friends/match_nick_tips_weibo";
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    private static final boolean ISLITE = false;
    public static final String KEY_ACTION = "key_action";
    public static final String KEY_APP_NAME = "oauth_app_name";
    public static final String KEY_ERROR_CODE = "key_error_code";
    public static final String KEY_ERROR_DETAIL = "key_error_detail";
    public static final String KEY_ERROR_MSG = "key_error_msg";
    public static final String KEY_PARAMS = "key_params";
    public static final String KEY_REQUEST_CODE = "key_request_code";
    public static final String KEY_RESPONSE = "key_response";
    public static final String LOGIN_INFO = "login_info";
    public static final String MOBILEQQ_PACKAGE_NAME = "com.tencent.mobileqq";
    public static final String MSG_CONNECTTIMEOUT_ERROR = "\u7f51\u7edc\u8fde\u63a5\u8d85\u65f6!";
    public static final String MSG_IMAGE_ERROR = "\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u8be5\u56fe\u7247\u662f\u5426\u6709\u6548";
    public static final String MSG_IO_ERROR = "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\uff0c\u8bf7\u68c0\u67e5\u540e\u91cd\u8bd5!";
    public static final String MSG_JSON_ERROR = "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u6709\u8bef!";
    public static final String MSG_LOCATION_TIMEOUT_ERROR = "\u5b9a\u4f4d\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u6216\u68c0\u67e5\u7f51\u7edc\u72b6\u51b5\uff01";
    public static final String MSG_LOCATION_VERIFY_ERROR = "\u5b9a\u4f4d\u5931\u8d25\uff0c\u9a8c\u8bc1\u5b9a\u4f4d\u7801\u9519\u8bef\uff01";
    public static final String MSG_NOT_CALL_ON_MAIN_THREAD = "\u6ca1\u6709\u5728\u4e3b\u7ebf\u7a0b\u8c03\u7528\uff01";
    public static final String MSG_NO_SDCARD = "\u68c0\u6d4b\u4e0d\u5230SD\u5361\uff0c\u65e0\u6cd5\u53d1\u9001\u8bed\u97f3\uff01";
    public static final String MSG_OPEN_BROWSER_ERROR = "\u6253\u5f00\u6d4f\u89c8\u5668\u5931\u8d25!";
    public static final String MSG_PARAM_APPSHARE_TOO_LOW = "\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u5e94\u7528\u5206\u4eab\u53ea\u652f\u6301\u624bQ5.0\u53ca\u5176\u4ee5\u4e0a\u7248\u672c";
    public static final String MSG_PARAM_ERROR = "\u4f20\u5165\u53c2\u6570\u6709\u8bef!";
    public static final String MSG_PARAM_IMAGE_ERROR = "\u7eaf\u56fe\u5206\u4eab\uff0cimageUrl \u4e0d\u80fd\u4e3a\u7a7a";
    public static final String MSG_PARAM_IMAGE_URL_FORMAT_ERROR = "\u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!";
    public static final String MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL = "\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247";
    public static final String MSG_PARAM_NULL_ERROR = "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a";
    public static final String MSG_PARAM_QQ_VERSION_ERROR = "\u4f4e\u7248\u672c\u624bQ\u4e0d\u652f\u6301\u8be5\u9879\u529f\u80fd!";
    public static final String MSG_PARAM_TARGETURL_ERROR = "targetUrl\u6709\u8bef";
    public static final String MSG_PARAM_TARGETURL_NULL_ERROR = "targetUrl\u4e3a\u5fc5\u586b\u9879\uff0c\u8bf7\u8865\u5145\u540e\u5206\u4eab";
    public static final String MSG_PARAM_TITLE_NULL_ERROR = "title\u4e0d\u80fd\u4e3a\u7a7a!";
    public static final String MSG_PARAM_VERSION_TOO_LOW = "\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u8bf7\u4e0b\u8f7d\u5b89\u88c5\u6700\u65b0\u7248\u624bQ";
    public static final String MSG_PUBLISH_VIDEO_ERROR = "\u8bf7\u9009\u62e9\u6709\u6548\u7684\u89c6\u9891\u6587\u4ef6";
    public static final String MSG_SHARE_GETIMG_ERROR = "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!";
    public static final String MSG_SHARE_NOSD_ERROR = "\u5206\u4eab\u56fe\u7247\u5931\u8d25\uff0c\u68c0\u6d4b\u4e0d\u5230SD\u5361!";
    public static final String MSG_SHARE_TO_QQ_ERROR = "\u5206\u4eab\u7684\u624b\u673aQQ\u5931\u8d25!";
    public static final String MSG_SHARE_TYPE_ERROR = "\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b";
    public static final String MSG_SOCKETTIMEOUT_ERROR = "\u7f51\u7edc\u8fde\u63a5\u8d85\u65f6!";
    public static final String MSG_UNKNOWN_ERROR = "\u672a\u77e5\u9519\u8bef!";
    public static final String MSG_URL_ERROR = "\u8bbf\u95eeurl\u6709\u8bef!";
    public static final String PACKAGE_QQ = "com.tencent.mobileqq";
    public static final String PACKAGE_QQ_PAD = "com.tencent.minihd.qq";
    public static final String PACKAGE_QZONE = "com.qzone";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_APP_ID = "appid";
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_CONSUMER_KEY = "oauth_consumer_key";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    public static final String PARAM_HOPEN_ID = "hopenid";
    public static final String PARAM_KEY_STR = "keystr";
    public static final String PARAM_KEY_TYPE = "keytype";
    public static final String PARAM_OPEN_ID = "openid";
    public static final String PARAM_PLATFORM = "platform";
    public static final String PARAM_PLATFORM_ID = "pf";
    public static final String PARAM_SCOPE = "scope";
    public static final String PREFERENCE_PF = "pfStore";
    public static final String QQ_APPID = "100686848";
    public static final int REQUEST_API = 10100;
    public static final int REQUEST_APPBAR = 10102;
    public static final int REQUEST_AVATER = 11102;
    public static final int REQUEST_LOGIN = 11101;
    public static final int REQUEST_OLD_QZSHARE = 11104;
    public static final int REQUEST_OLD_SHARE = 11103;
    public static final int REQUEST_QQ_FAVORITES = 10105;
    public static final int REQUEST_QQ_SHARE = 10103;
    public static final int REQUEST_QZONE_SHARE = 10104;
    public static final int REQUEST_SEND_TO_MY_COMPUTER = 10106;
    public static final int REQUEST_SHARE_TO_TROOP_BAR = 10107;
    public static final int REQUEST_SOCIAL_API = 11105;
    public static final int REQUEST_SOCIAL_H5 = 11106;
    public static final String SDK_BUILD = "5502";
    public static final String SDK_QUA = "V1_AND_OpenSDK_2.9.4_1077_RDM_B";
    private static final String SDK_VER = "2.9.4";
    public static final String SDK_VERSION = "2.9.4";
    public static final String SDK_VERSION_REPORT = "OpenSdk_2.9.4";
    public static final String SDK_VERSION_STRING = "Android_SDK_2.9.4";
    public static final String SIGNATRUE_QZONE = "ec96e9ac1149251acbb1b0c5777cae95";
    public static final String SOURCE_QQ = "QQ";
    public static final String SOURCE_QZONE = "qzone";
    public static final String STR_EMPTY = "";
    public static final int UI_ACTIVITY = 1;
    public static final int UI_CHECK_TOKEN = 3;
    public static final int UI_DIALOG = 2;
    public static final int UI_DOWNLOAD_QQ = 4;
    public static final int UI_NONE = -1;
    public static final String VIA_ACT_TYPE_EIGHTEEN = "18";
    public static final String VIA_ACT_TYPE_FIVE = "5";
    public static final String VIA_ACT_TYPE_NINETEEN = "19";
    public static final String VIA_ACT_TYPE_THREE = "3";
    public static final String VIA_ACT_TYPE_TWENTY_EIGHT = "28";
    public static final String VIA_BIND_GROUP = "ANDROIDSDK.BINDGROUP.XX";
    public static final String VIA_CALL_SOURCE_H5 = "2";
    public static final String VIA_CALL_SOURCE_SQ = "1";
    public static final String VIA_JOIN_GROUP = "ANDROIDQQ.JOININGROUP.XX";
    public static final String VIA_MAKE_FRIEND = "ANDROIDQQ.MAKEAFRIEND.XX";
    public static final String VIA_NO_VALUE = "0";
    public static final String VIA_REPORT_TYPE_BIND_GROUP = "18";
    public static final String VIA_REPORT_TYPE_DATALINE = "22";
    public static final String VIA_REPORT_TYPE_JOININ_GROUP = "13";
    public static final String VIA_REPORT_TYPE_MAKE_FRIEND = "14";
    public static final String VIA_REPORT_TYPE_QQFAVORITES = "21";
    public static final String VIA_REPORT_TYPE_SET_AVATAR = "12";
    public static final String VIA_REPORT_TYPE_SHARE_TO_QQ = "10";
    public static final String VIA_REPORT_TYPE_SHARE_TO_QZONE = "11";
    public static final String VIA_REPORT_TYPE_SHARE_TO_TROOPBAR = "23";
    public static final String VIA_REPORT_TYPE_SSO_LOGIN = "310";
    public static final String VIA_REPORT_TYPE_START_GROUP = "17";
    public static final String VIA_REPORT_TYPE_START_WAP = "16";
    public static final String VIA_REPORT_TYPE_WPA_STATE = "15";
    public static final String VIA_RESULT_FAIL = "1";
    public static final String VIA_RESULT_SUCCESS = "0";
    public static final String VIA_SDK = "2";
    public static final String VIA_SET_AVATAR = "ANDROIDSDK.SETAVATAR.XX";
    public static final String VIA_SET_AVATAR_SUCCEED = "ANDROIDSDK.SETAVATAR.SUCCEED";
    public static final String VIA_SHARE_TO_QQ = "ANDROIDQQ.SHARETOQQ.XX";
    public static final String VIA_SHARE_TO_QZONE = "ANDROIDQQ.SHARETOQZ.XX";
    public static final String VIA_SHARE_TO_TROOPBAR = "ANDROIDSDK.SHARETOTROOPBAR.XX";
    public static final String VIA_SHARE_TYPE_APP = "4";
    public static final String VIA_SHARE_TYPE_IMAGE = "2";
    public static final String VIA_SHARE_TYPE_IMAGE_TEXT = "1";
    public static final String VIA_SHARE_TYPE_INFO = "6";
    public static final String VIA_SHARE_TYPE_MUSIC = "3";
    public static final String VIA_SHARE_TYPE_TEXT = "5";
    public static final String VIA_SSO_LOGIN = "2";
    public static final String VIA_START_WAP = "ANDROIDSDK.STARTWPA.XX";
    public static final String VIA_TO_TYPE_QQ_DISCUSS_GROUP = "3";
    public static final String VIA_TO_TYPE_QQ_FRIEND = "1";
    public static final String VIA_TO_TYPE_QQ_GROUP = "1";
    public static final String VIA_TO_TYPE_QZONE = "4";
    public static final String VIA_WAP_STATE = "ANDROIDSDK.WPASTATE.XX";

}
