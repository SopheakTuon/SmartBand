package com.example.android.bluetoothlegatt.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;

import com.example.android.bluetoothlegatt.ble_service.BleCommand;
import com.example.android.bluetoothlegatt.ble_service.BroadcastCommand;
import com.example.android.bluetoothlegatt.constant.Constants;
import com.example.android.bluetoothlegatt.models.AlertModel;
import com.example.android.bluetoothlegatt.models.BroadcastData;
import com.example.android.bluetoothlegatt.models.DateModel;
import com.example.android.bluetoothlegatt.models.DisturbanceModel;
import com.example.android.bluetoothlegatt.models.SedentaryInfo;
import com.example.android.bluetoothlegatt.models.UserModel;

import java.util.Calendar;

public class CommandManager {
    private static final String TAG = "CommandManager";
    private static CommandManager instance;
    private static Context mContext;

    private CommandManager() {
    }

    public static synchronized CommandManager getInstance(Context context) {
        CommandManager commandManager;
        synchronized (CommandManager.class) {
            if (mContext == null) {
                mContext = context;
            }
            if (instance == null) {
                instance = new CommandManager();
            }
            commandManager = instance;
        }
        return commandManager;
    }

    public void setUserInfo(UserModel model) {
        Log.d(TAG, model.toString());
        byte[] data = new byte[13];
        data[0] = (byte) -85;
        data[1] = (byte) 0;
        data[2] = (byte) 10;
        data[3] = (byte) -1;
        data[4] = (byte) 116;
        data[5] = Byte.MIN_VALUE;
        data[7] = (byte) model.getAge();
        data[8] = (byte) Integer.parseInt(model.getHeight() == null ? Constants.VIA_RESULT_SUCCESS : model.getHeight());
        data[9] = (byte) Integer.parseInt(model.getWeight() == null ? Constants.VIA_RESULT_SUCCESS : model.getWeight());
        data[10] = (byte) Integer.parseInt(model.getSystaltic() == null ? Constants.VIA_RESULT_SUCCESS : model.getSystaltic());
        data[11] = (byte) Integer.parseInt(model.getDiastolic() == null ? Constants.VIA_RESULT_SUCCESS : model.getDiastolic());
        if (Constants.VIA_RESULT_SUCCESS.equals(model.getDistanceUnit())) {
            data[12] = (byte) 0;
        } else {
            data[12] = (byte) 1;
        }
        broadcastData(data);
//        AppApplication.data = data;
    }

    public void setTimeSync() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        byte[] data = new byte[14];
        data[0] = (byte) -85;
        data[1] = (byte) 0;
        data[2] = BleCommand.RequestType.SPI_FLASH_SET_BLE;
        data[3] = (byte) -1;
        data[4] = (byte) -109;
        data[5] = Byte.MIN_VALUE;
        data[7] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & year) >> 8);
        data[8] = (byte) (year & 255);
        data[9] = (byte) (month & 255);
        data[10] = (byte) (day & 255);
        data[11] = (byte) (hour & 255);
        data[12] = (byte) (minute & 255);
        data[13] = (byte) (second & 255);
        broadcastData(data);
    }

    public void setSyncData(long timeInMillis) {
        DateModel dateModel = new DateModel(timeInMillis);
        byte[] data = new byte[12];
        data[0] = (byte) -85;
        data[1] = (byte) 0;
        data[2] = (byte) 9;
        data[3] = (byte) -1;
        data[4] = (byte) 81;
        data[5] = Byte.MIN_VALUE;
        data[7] = (byte) (dateModel.year - 2000);
        data[8] = (byte) dateModel.month;
        data[9] = (byte) dateModel.day;
        data[10] = (byte) dateModel.hour;
        data[11] = (byte) dateModel.minute;
        broadcastData(data);
    }

    public void setAlertClock(AlertModel alertModel) {
        int i = 1;
        Log.d(TAG, alertModel.toString());
        byte[] data = new byte[11];
        data[0] = (byte) -85;
        data[1] = (byte) 0;
        data[2] = (byte) 8;
        data[3] = (byte) -1;
        data[4] = (byte) 115;
        data[5] = Byte.MIN_VALUE;
        data[6] = (byte) alertModel.identifier;
        if (!alertModel.isStatus()) {
            i = 0;
        }
        data[7] = (byte) i;
        data[8] = (byte) alertModel.hour;
        data[9] = (byte) alertModel.minute;
        data[10] = (byte) alertModel.repeat;
        broadcastData(data);
    }

    public void falldownWarn(int control) {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 4, (byte) -1, BleCommand.RequestType.SEND_FRIZZFW, Byte.MIN_VALUE, (byte) control});
    }

    public void realTimeAndOnceMeasure(int status, int control) {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 4, (byte) -1, (byte) 49, (byte) status, (byte) control});
    }

    public void smartWarn(int MessageId, int type) {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 5, (byte) -1, (byte) 114, Byte.MIN_VALUE, (byte) MessageId, (byte) type});
    }

    public void upHandLightScreen(int control) {
        byte[] bytes = new byte[]{(byte) -85, (byte) 0, (byte) 4, (byte) -1};
        bytes[1] = (byte) 119;
        bytes[2] = Byte.MIN_VALUE;
        bytes[3] = (byte) control;
        broadcastData(bytes);
    }

    public void onTimeMeasure(int control) {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 4, (byte) -1, (byte) 120, Byte.MIN_VALUE, (byte) control});
    }

    public void oneKeyMeasure(int control) {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 4, (byte) -1, (byte) 50, Byte.MIN_VALUE, (byte) control});
    }

    public void disturbanceModel() {
        DisturbanceModel disturbanceModel = new DisturbanceModel(mContext);
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 8, (byte) -1, (byte) 118, Byte.MIN_VALUE, (byte) disturbanceModel.isOn, (byte) disturbanceModel.startHour, (byte) disturbanceModel.startMinute, (byte) disturbanceModel.endHour, (byte) disturbanceModel.endMinute});
    }

    public void sedentaryWarn() {
        SedentaryInfo sedentaryInfo = new SedentaryInfo(mContext);
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 8, (byte) -1, (byte) 117, Byte.MIN_VALUE, (byte) sedentaryInfo.isOn, (byte) sedentaryInfo.startHour, (byte) sedentaryInfo.startMinute, (byte) sedentaryInfo.endHour, (byte) sedentaryInfo.endMinute});
    }

    public void findBand() {
        broadcastData(new byte[]{(byte) -85, (byte) 0, (byte) 3, (byte) -1, (byte) 113, Byte.MIN_VALUE});
    }

    private void broadcastData(byte[] data) {
//        if (AppApplication.isConnected) {
            BroadcastData bData = new BroadcastData(10);
            if (bData.data != null) {
                bData.data = null;
            }
            bData.data = data;
            Intent intent = new Intent(BroadcastCommand.DATA_RECEIVED_FROM_ACTIVITY);
            intent.putExtra(BroadcastData.keyword, bData);
            try {
                Log.d("lq", "broadcastData");
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
//        }
    }
}
