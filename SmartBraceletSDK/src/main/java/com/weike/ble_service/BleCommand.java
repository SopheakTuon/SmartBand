/*******************************************************************
 * @file BleCommand.java
 * @par Copyright (C) 2014-2015 MegaChips Corporation
 * @date 2014/02/26
 *******************************************************************/

package com.weike.ble_service;

public class BleCommand {

    private byte id;

    public BleCommand(byte b) {
        id = b;
    }

    public BleCommand(byte b, byte[] data) {
        id = b;
    }

    public byte[] getData() {
        byte[] data = makeData();
        return data;
    }

    public byte[] makeData() {
        byte[] data = null;
        if (id == RequestType.FRIZZ_RESET) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SET_LOGGING_MODE) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.SET_BLE_TIMER) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.SET_SAMPLING_FREQUENCY) {
            data = new byte[5];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_SET_SAMPLING_START) {
            data = new byte[5];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_SET_SAMPLING_STOP) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_SET_UART) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_SET_UART) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_SET_BLE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_SET_SAMPLING_START) {
            data = new byte[5];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_SET_SAMPLING_STOP) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_SET_BLE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_SET_DETAIL) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.START_ERASE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.START_ALL_ERASE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.START_HEART_RATE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SET_HEART_RATE) {
            data = new byte[44 + 1];
            data[0] = id;
        } else if (id == RequestType.STOP_HEART_RATE) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.GET_SENSOR_VERSION) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SWING_CALIBRATION_START) {
            data = new byte[1];
            data[0] = id;
        } else if (id == RequestType.SPI_FLASH_PPG_GET_DETAIL) {
            data = new byte[2];
            data[0] = id;
        } else if (id == RequestType.SEND_CMD) {
            data = new byte[3];
            data[0] = id;
        } else if (id == RequestType.ERASE_SPIFLASH) {
            data = new byte[9];
            data[0] = id;
        }
        return data;
    }

    // Make data to control sensor
    // 将数据控制传感器
    public static byte[] Set_SensorControl_Param(int cmd, int sensor_id,
                                                 int sensor_id2, int param_length, int param[]) {
        byte[] data = new byte[7 + param_length * 4];
        data[0] = RequestType.SENSOR_CONTROL;
        data[1] = (byte) cmd;
        data[2] = (byte) sensor_id;
        data[3] = 0x00; // dummy
        data[4] = GPIO_mode.GPIO_DISABLE;
        data[5] = (byte) sensor_id2;
        data[6] = (byte) param_length; // parameter num
        for (int i = 0; i < param_length; i++) {
            data[7 + i * 4] = (byte) ((param[i] & 0xFF000000) >> 24);
            data[8 + i * 4] = (byte) ((param[i] & 0x00FF0000) >> 16);
            data[9 + i * 4] = (byte) ((param[i] & 0x0000FF00) >> 8);
            data[10 + i * 4] = (byte) ((param[i] & 0x000000FF));
        }
        return data;
    }

    // Make data to activate sensor
//    public static byte[] registerListener(int sensor_id) {
//        byte[] data = new byte[7];
//        data[0] = BleCommand.RequestType.SENSOR_CONTROL;
//        data[1] = BleCommand.HUB_mng_cmd.HUB_MGR_CMD_SET_SENSOR_ACTIVATE;
//        data[2] = (byte) sensor_id;
//        data[3] = 0x00; // dummy
//        data[4] = BleCommand.GPIO_mode.GPIO_ENABLE;
//        data[5] = (byte) BleCommand.SensorID.HUB_MGR_ID;
//        data[6] = 0x00; // parameter number
//        return data;
//    }

    // Make data to deactivate sensor
//    public static byte[] unregisterListener(int sensor_id) {
//        byte[] data = new byte[7];
//        data[0] = BleCommand.RequestType.SENSOR_CONTROL;
//        data[1] = BleCommand.HUB_mng_cmd.HUB_MGR_CMD_DEACTIVATE_SENSOR;
//        data[2] = (byte) sensor_id;
//        data[3] = 0x00; // dummy
//        data[4] = BleCommand.GPIO_mode.GPIO_DISABLE;
//        data[5] = (byte) BleCommand.SensorID.HUB_MGR_ID;
//        data[6] = 0x00; // parameter number
//        return data;
//    }

    // Make data to set sensor interval
//    public static byte[] setinterval(int sensor_id, int interval) {
//        byte[] data = new byte[7 + 4];
//        data[0] = BleCommand.RequestType.SENSOR_CONTROL;
//        data[1] = BleCommand.HUB_mng_cmd.HUB_MGR_CMD_SET_SENSOR_INTERVAL;
//        data[2] = (byte) sensor_id;
//        data[3] = 0x00; // dummy
//        data[4] = BleCommand.GPIO_mode.GPIO_DISABLE;
//        data[5] = (byte) BleCommand.SensorID.HUB_MGR_ID;
//        data[6] = 0x01; // parameter number 0x01 = 4byte
//        data[7] = (byte) ((interval & 0xFF000000) >> 24);
//        data[8] = (byte) ((interval & 0x00FF0000) >> 16);
//        data[9] = (byte) ((interval & 0x0000FF00) >> 8);
//        data[10] = (byte) ((interval & 0x000000FF));
//        return data;
//    }

    // Make data to send frizz FW
    public static byte[] setfrizzFW(byte id, int seg, int total, int length,
                                    byte param[], long check_sum) {
        byte[] data = new byte[9 + length];
        data[0] = id;
        data[1] = (byte) ((seg & 0x0000FF00) >> 8);
        data[2] = (byte) ((seg & 0x000000FF));
        data[3] = (byte) ((total & 0x0000FF00) >> 8);
        data[4] = (byte) ((total & 0x000000FF));
        data[5] = (byte) ((length & 0x0000FF00) >> 8);
        data[6] = (byte) ((length & 0x000000FF));
        for (int i = 0; i < length; i++) {
            data[7 + i] = param[i];
        }

        data[9 + length - 2] = (byte) ((check_sum & 0x0000FF00) >> 8);
        data[9 + length - 1] = (byte) ((check_sum & 0x000000FF));
        return data;
    }

    // Request type from android to host
    public class RequestType {
        public static final byte SENSOR_CONTROL = 0x0;
        public static final byte FRIZZ_RESET = 0x1;
        public static final byte SET_LOGGING_MODE = 0x2;
        public static final byte SET_BLE_TIMER = 0x3;
        public static final byte START_SENSOR_DUMP = 0x4;
        public static final byte STOP_SENSOR_DUMP = 0x5;
        public static final byte EXIT_SENSOR_DUMP = 0x6;
        public static final byte SET_SAMPLING_FREQUENCY = 0x7;
        public static final byte SPI_FLASH_SET_SAMPLING_START = 0x8;
        public static final byte SPI_FLASH_SET_SAMPLING_STOP = 0x9;
        public static final byte SPI_FLASH_SET_UART = 0xA;
        public static final byte SPI_FLASH_SET_BLE = 0xB;
        public static final byte START_ERASE = 0xC;
        public static final byte START_ALL_ERASE = 0xD;
        public static final byte SWING_CALIBRATION_START = 0xE;
        public static final byte GET_SENSOR_VERSION = 0xF;
        public static final byte SEND_CMD = 0x10;
        public static final byte SEND_FRIZZFW = 0x11;
        public static final byte ERASE_SPIFLASH = 0x12;
        public static final byte SEND_CHIGNONFW = 0x13;

        public static final byte START_HEART_RATE = 0x40;
        public static final byte SET_HEART_RATE = 0x00;
        public static final byte STOP_HEART_RATE = 0x42;

        public static final byte SPI_FLASH_PPG_SET_SAMPLING_START = 0x43;
        public static final byte SPI_FLASH_PPG_SET_SAMPLING_STOP = 0x44;
        public static final byte SPI_FLASH_PPG_SET_BLE = 0x45;
        public static final byte SPI_FLASH_PPG_SET_DETAIL = 0x46;
        public static final byte SPI_FLASH_PPG_GET_DETAIL = 0x47;
        public static final byte SPI_FLASH_PPG_SET_UART = 0x48;
    }

    // HUB manager command
    public class HUB_mng_cmd {
        public static final int HUB_MGR_CMD_DEACTIVATE_SENSOR = 0x00;
        public static final int HUB_MGR_CMD_SET_SENSOR_ACTIVATE = 0x01;
        public static final int HUB_MGR_CMD_SET_SENSOR_INTERVAL = 0x02;
    }

    // GPIO event handler mode of host
    public class GPIO_mode {
        public static final int GPIO_DISABLE = 0x00;
        public static final int GPIO_ENABLE = 0x01;
    }

    // Logging mode of host
    public class Loging_mode {
        public static final int ENABLE_LOGGING = 0x00;
        public static final int DISABLE_LOGGING = 0x01;
    }

    // Packet transmission interval from host to android
    public class BLE_timer_mode {
        public static final int HIGH_SPEED_MODE = 0x00;
        public static final int LOW_SPEED_MODE = 0x01;
    }

    // kind of data
    public class kind_data {
        public static final int OUTPUT = 0x80;
        public static final int RESPONCE = 0x84;
    }

    public class MessageId {
        public static final int INCOMING_CALLING = 1;
        public static final int MISSED_CALL = 2;
        public static final int MESSAGES = 3;
        public static final int MAIL = 4;
        public static final int Calendar = 5;
        public static final int FaceTime = 6;
        public static final int QQ = 7;
        public static final int Skype = 8;
        public static final int wechat = 9;
        public static final int WhatsApp = 10;
        public static final int Gmail = 11;
        public static final int Hangout = 12;
        public static final int Inbox = 13;
        public static final int Line = 14;
        public static final int Twitter = 15;
        public static final int Facebook = 16;
        public static final int Facebook_Messenger= 17;
        public static final int Instangram = 18;
        public static final int Weibo = 19;
    }



}
