package com.example.android.bluetoothlegatt.ble_service;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

public class BleCommand {
    private byte id;

    public class BLE_timer_mode {
        public static final int HIGH_SPEED_MODE = 0;
        public static final int LOW_SPEED_MODE = 1;
    }

    public class GPIO_mode {
        public static final int GPIO_DISABLE = 0;
        public static final int GPIO_ENABLE = 1;
    }

    public class HUB_mng_cmd {
        public static final int HUB_MGR_CMD_DEACTIVATE_SENSOR = 0;
        public static final int HUB_MGR_CMD_SET_SENSOR_ACTIVATE = 1;
        public static final int HUB_MGR_CMD_SET_SENSOR_INTERVAL = 2;
    }

    public class Loging_mode {
        public static final int DISABLE_LOGGING = 1;
        public static final int ENABLE_LOGGING = 0;
    }

    public class MessageId {
        public static final int Calendar = 5;
        public static final int FaceTime = 6;
        public static final int Facebook = 16;
        public static final int Facebook_Messenger = 17;
        public static final int Gmail = 11;
        public static final int Hangout = 12;
        public static final int INCOMING_CALLING = 1;
        public static final int Inbox = 13;
        public static final int Instangram = 18;
        public static final int Line = 14;
        public static final int MAIL = 4;
        public static final int MESSAGES = 3;
        public static final int MISSED_CALL = 2;
        public static final int QQ = 7;
        public static final int Skype = 8;
        public static final int Twitter = 15;
        public static final int Weibo = 19;
        public static final int WhatsApp = 10;
        public static final int wechat = 9;
    }

    public class RequestType {
        public static final byte ERASE_SPIFLASH = (byte) 18;
        public static final byte EXIT_SENSOR_DUMP = (byte) 6;
        public static final byte FRIZZ_RESET = (byte) 1;
        public static final byte GET_SENSOR_VERSION = (byte) 15;
        public static final byte SEND_CHIGNONFW = (byte) 19;
        public static final byte SEND_CMD = (byte) 16;
        public static final byte SEND_FRIZZFW = (byte) 17;
        public static final byte SENSOR_CONTROL = (byte) 0;
        public static final byte SET_BLE_TIMER = (byte) 3;
        public static final byte SET_HEART_RATE = (byte) 0;
        public static final byte SET_LOGGING_MODE = (byte) 2;
        public static final byte SET_SAMPLING_FREQUENCY = (byte) 7;
        public static final byte SPI_FLASH_PPG_GET_DETAIL = (byte) 71;
        public static final byte SPI_FLASH_PPG_SET_BLE = (byte) 69;
        public static final byte SPI_FLASH_PPG_SET_DETAIL = (byte) 70;
        public static final byte SPI_FLASH_PPG_SET_SAMPLING_START = (byte) 67;
        public static final byte SPI_FLASH_PPG_SET_SAMPLING_STOP = (byte) 68;
        public static final byte SPI_FLASH_PPG_SET_UART = (byte) 72;
        public static final byte SPI_FLASH_SET_BLE = (byte) 11;
        public static final byte SPI_FLASH_SET_SAMPLING_START = (byte) 8;
        public static final byte SPI_FLASH_SET_SAMPLING_STOP = (byte) 9;
        public static final byte SPI_FLASH_SET_UART = (byte) 10;
        public static final byte START_ALL_ERASE = (byte) 13;
        public static final byte START_ERASE = (byte) 12;
        public static final byte START_HEART_RATE = (byte) 64;
        public static final byte START_SENSOR_DUMP = (byte) 4;
        public static final byte STOP_HEART_RATE = (byte) 66;
        public static final byte STOP_SENSOR_DUMP = (byte) 5;
        public static final byte SWING_CALIBRATION_START = (byte) 14;
    }

    public class kind_data {
        public static final int OUTPUT = 128;
        public static final int RESPONCE = 132;
    }

    public BleCommand(byte b) {
        this.id = b;
    }

    public BleCommand(byte b, byte[] data) {
        this.id = b;
    }

//    public byte[] getData() {
//        return makeData();
//    }

//    public byte[] makeData() {
//        if (this.id == (byte) 1) {
//            return new byte[]{this.id};
//        } else if (this.id == (byte) 2) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == (byte) 3) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 7) {
//            data = new byte[5];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 8) {
//            data = new byte[5];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 9) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 10) {
//            return new byte[]{this.id};
//        } else if (this.id == 72) {
//            return new byte[]{this.id};
//        } else if (this.id == 11) {
//            return new byte[]{this.id};
//        } else if (this.id == 67) {
//            data = new byte[5];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 68) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 69) {
//            return new byte[]{this.id};
//        } else if (this.id == 70) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 12) {
//            return new byte[]{this.id};
//        } else if (this.id == 13) {
//            return new byte[]{this.id};
//        } else if (this.id == 64) {
//            return new byte[]{this.id};
//        } else if (this.id == null) {
//            data = new byte[45];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 66) {
//            return new byte[]{this.id};
//        } else if (this.id == 15) {
//            return new byte[]{this.id};
//        } else if (this.id == 14) {
//            return new byte[]{this.id};
//        } else if (this.id == 71) {
//            data = new byte[2];
//            data[0] = this.id;
//            return data;
//        } else if (this.id == 16) {
//            data = new byte[3];
//            data[0] = this.id;
//            return data;
//        } else if (this.id != 18) {
//            return null;
//        } else {
//            data = new byte[9];
//            data[0] = this.id;
//            return data;
//        }
//    }

    public static byte[] Set_SensorControl_Param(int cmd, int sensor_id, int sensor_id2, int param_length, int[] param) {
        byte[] data = new byte[((param_length * 4) + 7)];
        data[0] = (byte) 0;
        data[1] = (byte) cmd;
        data[2] = (byte) sensor_id;
        data[3] = (byte) 0;
        data[4] = (byte) 0;
        data[5] = (byte) sensor_id2;
        data[6] = (byte) param_length;
        for (int i = 0; i < param_length; i++) {
            data[(i * 4) + 7] = (byte) ((param[i] & ViewCompat.MEASURED_STATE_MASK) >> 24);
            data[(i * 4) + 8] = (byte) ((param[i] & 16711680) >> 16);
            data[(i * 4) + 9] = (byte) ((param[i] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            data[(i * 4) + 10] = (byte) (param[i] & 255);
        }
        return data;
    }

    public static byte[] setfrizzFW(byte id, int seg, int total, int length, byte[] param, long check_sum) {
        byte[] data = new byte[(length + 9)];
        data[0] = id;
        data[1] = (byte) ((seg & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        data[2] = (byte) (seg & 255);
        data[3] = (byte) ((total & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        data[4] = (byte) (total & 255);
        data[5] = (byte) ((length & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        data[6] = (byte) (length & 255);
        for (int i = 0; i < length; i++) {
            data[i + 7] = param[i];
        }
        data[(length + 9) - 2] = (byte) ((int) ((65280 & check_sum) >> 8));
        data[(length + 9) - 1] = (byte) ((int) (255 & check_sum));
        return data;
    }
}
