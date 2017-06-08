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

package com.example.android.bluetoothlegatt.ble_service;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.android.bluetoothlegatt.constant.Constants;
import com.example.android.bluetoothlegatt.models.BroadcastData;
import com.example.android.bluetoothlegatt.models.DataPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

/**
 * Service for managing connection and data communication with a GATT server hosted on a
 * given Bluetooth LE device.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothLeService extends Service {
    private final static String TAG = BluetoothLeService.class.getSimpleName();

    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;
    private int mConnectionState = STATE_DISCONNECTED;

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";

    int mState = 0;

    //CLIENT_CHARACTERISTIC_CONFIG
    public static final UUID CCCD;
    public static final UUID RX_CHAR_UUID;
    public static final UUID RX_SERVICE_UUID;
    public static final UUID TX_CHAR_UUID;

    private static final int FREE = 0;
    private static final int SEND_PACKET_SIZE = 20;

    private boolean final_packet;
    private boolean first_packet;
    private int send_data_pointer;
    private boolean packet_send;

    private byte[] send_data;
    private int packet_counter;

    public ArrayList<byte[]> data_queue;

    static {
        CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        RX_SERVICE_UUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
        RX_CHAR_UUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
        TX_CHAR_UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    }

    private int ble_status;
    private boolean sendingStoredData;
    private Timer mTimer;
    private DataFromActivityReceiver dataFromActivityReceiver;

    public BluetoothLeService() {
        this.bleDataHandler = new BleDataHandler();
        this.send_data_pointer = FREE;
        this.packet_counter = FREE;
        this.packet_send = false;
        this.ble_status = FREE;
        this.data_queue = new ArrayList();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (this.dataFromActivityReceiver == null) {
            this.dataFromActivityReceiver = new DataFromActivityReceiver();
            LocalBroadcastManager.getInstance(this).registerReceiver(this.dataFromActivityReceiver, makeGattUpdateIntentFilter());
        }
    }

    class DataFromActivityReceiver extends BroadcastReceiver {
        DataFromActivityReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BroadcastCommand.DATA_RECEIVED_FROM_ACTIVITY)) {
                BroadcastData bData = (BroadcastData) intent.getSerializableExtra(BroadcastData.keyword);
                if (bData.commandID == 0) {
                } else if (bData.commandID == 10) {
                    Log.d(BluetoothLeService.TAG, "BLE_RECEIVE_DATA");
                    BluetoothLeService.this.BLE_send_data_set((byte[]) bData.data, false);
                }
            }
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastCommand.DATA_RECEIVED_FROM_ACTIVITY);
        return intentFilter;
    }

    // Implements callback methods for GATT events that the app cares about.  For example,
    // connection change and services discovered.
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                mConnectionState = STATE_CONNECTED;
                broadcastUpdate(intentAction);
                Log.i(TAG, "Connected to GATT server.");
                // Attempts to discover services after successful connection.
                Log.i(TAG, "Attempting to start service discovery:" +
                        mBluetoothGatt.discoverServices());

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                mConnectionState = STATE_DISCONNECTED;
                Log.i(TAG, "Disconnected from GATT server.");
                broadcastUpdate(intentAction);
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                enableTXNotification();
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
//                List<BluetoothGattService> bluetoothLeServices = mBluetoothGatt.getServices();
//                List<BluetoothGattCharacteristic> bluetoothGattCharacteristics;
//                for (BluetoothGattService bluetoothGattService : bluetoothLeServices) {
//                    bluetoothGattCharacteristics = bluetoothGattService.getCharacteristics();
//                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattCharacteristics) {
//                        mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
//                    }
//                }
                List<BluetoothGattService> services = gatt.getServices();
                Log.i("onServicesDiscovered", services.toString());
            } else {
                Log.w(TAG, "onServicesDiscovered received: " + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic,
                                         int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                byte[] data = characteristic.getValue();
                StringBuilder stringBuilder = new StringBuilder(data.length);
                int length = data.length;
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(String.format("%02X ", new Object[]{Byte.valueOf(data[i])}));
                }
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
                Log.d("onCharacteristicRead", stringBuilder.toString());
            }

//            setNotifyNextSensor(gatt);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
//            byte[] data = characteristic.getValue();
//            StringBuilder stringBuilder = new StringBuilder(data.length);
//            int length = data.length;
//            for (int i = 0; i < length; i++) {
//                stringBuilder.append(String.format("%02X ", new Object[]{Byte.valueOf(data[i])}));
//            }
//            String uuid = characteristic.getService().getUuid().toString();
//            String characteristicUUID = characteristic.getUuid().toString();
//            Log.d("onCharacteristicChanged", stringBuilder.toString());
//            if (uuid.equals("0aabcdef-1111-2222-0000-facebeadaaaa") && characteristicUUID.equals("facebead-ffff-eeee-0004-facebeadaaaa")) {
//                BluetoothLeService.this.broadcastUpdate(Constants.ACTION_MAIN_DATA_ECG_ALL_DATA, stringBuilder.toString());
//            } else if (uuid.equals("0aabcdef-1111-2222-0000-facebeadaaaa") && characteristicUUID.equals("facebead-ffff-eeee-0005-facebeadaaaa")) {
//                BluetoothLeService.this.broadcastUpdate(Constants.ACTION_MAIN_DATA_PW, stringBuilder.toString());
//            } else if (uuid.equals("1aabcdef-1111-2222-0000-facebeadaaaa")) {
//                BluetoothLeService.this.sendBindBroadcast(stringBuilder.toString());
//            } else {
////                broadcastUpdate(ACTION_DATA_AVAILABLE, stringBuilder.toString());
//                BluetoothLeService.this.sendDataBroadcast(stringBuilder.toString());
//            }
            BluetoothLeService.this.broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
//            byte[] received = characteristic.getValue();
//            Log.d("lq", "onCharacteristicChanged");
//            String temp_text = "";
//            for (int i = BluetoothLeService.FREE; i < received.length; i += BluetoothLeService.STATE_CONNECTING) {
//                temp_text = temp_text + Integer.toHexString(received[i] & 255) + " ";
//            }
//            Log.d("onCharacteristicChanged", characteristic.getUuid().toString() + " : " + temp_text);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//            readNextSensor(gatt);
//            if (status == BluetoothGatt.GATT_SUCCESS) {
////                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
//
//
//                final byte[] data = characteristic.getValue();
//                if (data != null && data.length > 0) {
//                    final StringBuilder stringBuilder = new StringBuilder(data.length);
//                    for (byte byteChar : data)
//                        stringBuilder.append(String.format("%02X ", byteChar));
//
//                    Log.d("onCharacteristicWrite", "" +  new String(data) + "\n" + stringBuilder.toString());
//                }
//
//                mState++;
//            }
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        }
    };

    private void BLE_send_data_set(byte[] data, boolean retry_status) {
//        if (this.ble_status == 0 && this.mConnectionState == STATE_CONNECTED) {
        this.ble_status = STATE_CONNECTING;
        if (this.data_queue.size() != 0) {
            this.send_data = this.data_queue.get(FREE);
            this.sendingStoredData = false;
        } else {
            this.send_data = data;
        }
        this.packet_counter = FREE;
        this.send_data_pointer = FREE;
        this.first_packet = true;
        BLE_data_send();
        if (this.data_queue.size() != 0) {
            this.data_queue.remove(FREE);
        }
        if (this.data_queue.size() == 0 && this.mTimer != null) {
            this.mTimer.cancel();
        }
//        } else if (!this.sendingStoredData) {
//            this.data_queue.add(data);
////            start_timer();
//            Intent intent = new Intent(BroadcastCommand.ACTION_BLE_SEND_REQUEST_DENIED);
//            BroadcastData bData = new BroadcastData();
//            bData.data = null;
//            intent.putExtra(BroadcastData.keyword, bData);
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//        } else if (!retry_status) {
//            this.data_queue.add(data);
//        }
    }

    public static String byte2HexStr(byte[] b) {
//        String stmp = BuildConfig.VERSION_NAME;
        String stmp = "1.0";
        StringBuilder sb = new StringBuilder("");
        for (int n = FREE; n < b.length; n += STATE_CONNECTING) {
            String str;
//            stmp = Integer.toHexString(b[n] & BallSpinFadeLoaderIndicator.ALPHA);
            stmp = Integer.toHexString(b[n]);
            if (stmp.length() == STATE_CONNECTING) {
                str = Constants.VIA_RESULT_SUCCESS + stmp;
            } else {
                str = stmp;
            }
            sb.append(str);
//            sb.append(CommonConsts.SPACE);
        }
        return sb.toString().toUpperCase().trim();
    }

    private void BLE_data_send() {
        int err_count = FREE;
        while (!this.final_packet) {
            byte[] temp_buffer;
            int send_data_pointer_save = this.send_data_pointer;
            boolean first_packet_save = this.first_packet;
            int i;
            if (this.first_packet) {
                if (this.send_data.length - this.send_data_pointer > SEND_PACKET_SIZE) {
                    temp_buffer = new byte[SEND_PACKET_SIZE];
                    for (i = FREE; i < SEND_PACKET_SIZE; i += STATE_CONNECTING) {
                        temp_buffer[i] = this.send_data[this.send_data_pointer];
                        this.send_data_pointer += STATE_CONNECTING;
                    }
                } else {
                    temp_buffer = new byte[(this.send_data.length - this.send_data_pointer)];
                    for (i = FREE; i < temp_buffer.length; i += STATE_CONNECTING) {
                        temp_buffer[i] = this.send_data[this.send_data_pointer];
                        this.send_data_pointer += STATE_CONNECTING;
                    }
                    this.final_packet = true;
                }
                this.first_packet = false;
            } else {
                if (this.send_data.length - this.send_data_pointer >= SEND_PACKET_SIZE) {
                    temp_buffer = new byte[SEND_PACKET_SIZE];
                    temp_buffer[FREE] = (byte) this.packet_counter;
                    for (i = STATE_CONNECTING; i < SEND_PACKET_SIZE; i += STATE_CONNECTING) {
                        temp_buffer[i] = this.send_data[this.send_data_pointer];
                        this.send_data_pointer += STATE_CONNECTING;
                    }
                } else {
                    this.final_packet = true;
                    temp_buffer = new byte[((this.send_data.length - this.send_data_pointer) + STATE_CONNECTING)];
                    temp_buffer[FREE] = (byte) this.packet_counter;
                    for (i = STATE_CONNECTING; i < temp_buffer.length; i += STATE_CONNECTING) {
                        temp_buffer[i] = this.send_data[this.send_data_pointer];
                        this.send_data_pointer += STATE_CONNECTING;
                    }
                }
                this.packet_counter += STATE_CONNECTING;
            }
            this.packet_send = false;
            boolean status = writeRXCharacteristic(temp_buffer);
            Log.d("lq", "send:" + byte2HexStr(temp_buffer) + "  packet_counter:" + this.packet_counter);
            if (!status && err_count < 3) {
                err_count += STATE_CONNECTING;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                Log.e(TAG, "writeRXCharacteristic false");
                this.send_data_pointer = send_data_pointer_save;
                this.first_packet = first_packet_save;
                this.packet_counter--;
            }
            for (int wait_counter = FREE; wait_counter < 5 && !this.packet_send; wait_counter += STATE_CONNECTING) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e2) {
                }
            }
        }
        this.final_packet = false;
        this.ble_status = FREE;
    }

    public void enableTXNotification() {
        BluetoothGattService RxService = this.mBluetoothGatt.getService(RX_SERVICE_UUID);
        if (RxService == null) {
//            showMessage("Rx service not found!");
//            broadcastUpdate(BroadcastCommand.DEVICE_DOES_NOT_SUPPORT_UART);
            return;
        }
        BluetoothGattCharacteristic TxChar = RxService.getCharacteristic(TX_CHAR_UUID);
        if (TxChar == null) {
//            showMessage("Tx charateristic not found!");
//            broadcastUpdate(BroadcastCommand.DEVICE_DOES_NOT_SUPPORT_UART);
            return;
        }
        Log.d("Moon", "-----  enbale tx -----");
        this.mBluetoothGatt.setCharacteristicNotification(TxChar, true);
        BluetoothGattDescriptor descriptor = TxChar.getDescriptor(CCCD);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        this.mBluetoothGatt.writeDescriptor(descriptor);
    }

    /**
     * @param data
     */
    private void sendBindBroadcast(String data) {
        String cmdType = data.substring(9, 11);
        if (cmdType.equals(Constants.CMD.DEVICE_MATCH_ACK)) {
            Constants.isMatchInfo = true;
            broadcastUpdate(Constants.ACTION_GATT_DEVICE_MATCH_ACK, Long.valueOf(data.substring(15, 17), 16));
        } else if (cmdType.equals(Constants.CMD.DEVICE_UNBIND_ACK)) {
            String values = data.substring(15, 17);
            broadcastUpdate(Constants.ACTION_GATT_DEVICE_UNBIND_ACK, Long.valueOf(values, 16));
        } else if (cmdType.equals(Constants.CMD.DEVICE_BIND_REQUEST)) {
            broadcastUpdate(Constants.ACTION_GATT_DEVICE_BIND_REQUEST);
        }
    }

    /**
     * @param data
     */
    private void sendDataBroadcast(String data) {
        Log.v(TAG, "Send Data = " + data);
        if (!data.equals("CF")) {
            String dataType = data.substring(9, 11);
            Log.v(TAG, "Data CMD =========== " + dataType);
            if (Constants.CMD.DATA_HEART_RATE.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_HR, parseSingeData(data));
                return;
            }
            if (Constants.CMD.DATA_MOOD.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_MOOD, parseMoodIntData(data));
                return;
            }
            if (Constants.CMD.DATA_FATIGUE.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_FATIGUE, parseMoodIntData(data));
                return;
            }
            if (Constants.CMD.DATA_BREATH_RATE.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_BREATH, parseBRData(data));
                return;
            }
            if (Constants.CMD.DATA_KLL.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_KLL, parseSingeData(data));
                return;
            }
            if (Constants.CMD.DATA_SLEEP.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_SLEEP, parseSleepData(data));
                return;
            }
            if (Constants.CMD.DATA_BLOOD_PRESSURE.equals(dataType)) {
                Log.v(TAG, "bp = " + data);
                broadcastUpdate(Constants.ACTION_MAIN_DATA_BP, parseBpData(data));
                return;
            }
            if (Constants.CMD.DATA_ECG.equals(dataType)) {
                broadcastUpdate(Constants.ACTION_MAIN_DATA_ECG, parseEcgData(data));
                return;
            }
        }

    }


    /**
     * @param data
     * @return value of battery
     */
    private int pareseBatteryData(String data) {
        return Integer.parseInt(data.substring(15, 17), 16);
    }

    /**
     * @param data
     * @return
     */
    private String parseSingeData(String data) {
        String dataStr = data.substring(27, 38);
        return new StringBuilder(String.valueOf((long) Integer.parseInt(dataStr.substring(9, 11) + dataStr.substring(6, 8) + dataStr.substring(3, 5) + dataStr.substring(0, 2), 16))).toString();
    }

    /**
     * @param data
     * @return
     */
    private long parseSleepData(String data) {
        String dataStr = data.substring(27, 38);
        String dateStr = data.substring(15, 26);
        return (long) Integer.parseInt(dataStr.substring(9, 11) + dataStr.substring(6, 8) + dataStr.substring(3, 5) + dataStr.substring(0, 2), 16);
    }

    /**
     * @param data
     * @return
     */
    private String parseBpData(String data) {
        Log.v(TAG, "bp data = " + data);
        String dataStr = data.substring(27, 32);
        Log.v(TAG, "bp dataStr = " + dataStr);
        return dataStr;
    }

    /**
     * @param data
     * @return
     */
    private String parseEcgData(String data) {
        Log.v(TAG, "Ecg data = " + data);
        String dataStr = data.substring(27, 29);
        Log.v(TAG, "Ecg dataStr = " + dataStr);
        return dataStr;
    }

    /**
     * @param data
     * @return
     */
    private String parseBRData(String data) {
        String dataStr = data.substring(27, 29);
        Log.v(TAG, "BR DATA = " + dataStr);
        return new StringBuilder(String.valueOf((long) Integer.parseInt(dataStr, 16))).toString();
    }

    /**
     * @param data
     * @return
     */
    private String parseMoodIntData(String data) {
        return data.substring(27, 29);
    }

    /**
     * @param action
     * @param dataf
     */
    @Deprecated
    private void broadcastUpdate(String action, long dataf) {
        Intent intent = new Intent(action);
        intent.putExtra(action, dataf);
        sendBroadcast(intent);
    }

    /**
     * @param action
     */
    @Deprecated
    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    /**
     * @param action
     * @param data
     */
    @Deprecated
    private void broadcastUpdate(final String action,
                                 final String data) {
        final Intent intent = new Intent(action);
        if (ACTION_DATA_AVAILABLE.equals(action)) {
            intent.putExtra(EXTRA_DATA, data);
        } else {
            intent.putExtra(action, data);
        }
        sendBroadcast(intent);
    }

    private BleDataHandler bleDataHandler;

    private void broadcastUpdate(String action, BluetoothGattCharacteristic characteristic) {
        Intent intent = new Intent(action);
        if (!TX_CHAR_UUID.equals(characteristic.getUuid())) {
            return;
        }
//        if (this.ble_status == 0 || this.ble_status == STATE_CONNECTED) {
        this.ble_status = STATE_CONNECTED;
        byte[] received = characteristic.getValue();
        Log.d("lq", "received:" + byte2HexStr(received));
        Log.d("lq", "received:" + bytesToByteString(received));
        if (this.bleDataHandler.add_data(received)) {
            int packet_status = this.bleDataHandler.check_packet();
            if (packet_status == 0) {
                DataPacket dataPacket = this.bleDataHandler.get_packet();
                if (dataPacket != null) {
                    Log.d("Data", "Package Data: " + bytesToByteString(dataPacket.data));
                    BroadcastData bData = new BroadcastData(2);
                    bData.setReceives(received);
                    bData.data = dataPacket;
                    intent.putExtra(BroadcastData.keyword, bData);
                    sendBroadcast(intent);
                }
                this.ble_status = FREE;
                return;
            } else if (packet_status != STATE_CONNECTING && packet_status == STATE_CONNECTED) {
                this.ble_status = FREE;
                return;
            } else {
                return;
            }
        }
//            this.bleDataHandler.clear_packet();
        this.ble_status = FREE;
//        } else if (this.ble_status == STATE_CONNECTING) {
//            if (this.final_packet) {
//                this.final_packet = false;
//                this.ble_status = FREE;
//            }
//            if (characteristic.getValue().length == STATE_CONNECTING) {
//                this.ble_status = FREE;
//            } else {
//                this.ble_status = FREE;
//            }
//        }
    }

    private String bytesToByteString(byte[] bytes) {
        String btyesString = "";
        for (int i = 0; i < bytes.length; i++) {
            btyesString += " " + bytes[i];
        }
        return btyesString;
    }

    private String bytesToByteString(ArrayList<Byte> bytes) {
        String btyesString = "";
        for (int i = 0; i < bytes.size(); i++) {
            btyesString += " " + bytes.get(i);
        }
        return btyesString;
    }

    public class LocalBinder extends Binder {
        public BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();

    /**
     * Initializes a reference to the local Bluetooth adapter.
     *
     * @return Return true if the initialization is successful.
     */
    public boolean initialize() {
        // For API level 18 and above, get a reference to BluetoothAdapter through
        // BluetoothManager.
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }

        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }

        return true;
    }

    /**
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param address The device address of the destination device.
     * @return Return true if the connection is initiated successfully. The connection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public boolean connect(final String address) {
        if (mBluetoothAdapter == null || address == null) {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }

        // Previously connected device.  Try to reconnect.
        if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress)
                && mBluetoothGatt != null) {
            Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
            if (mBluetoothGatt.connect()) {
                mConnectionState = STATE_CONNECTING;
                return true;
            } else {
                return false;
            }
        }

        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Log.w(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        // We want to directly connect to the device, so we are setting the autoConnect
        // parameter to false.
        mBluetoothGatt = device.connectGatt(this, false, mGattCallback);
//        Log.d(TAG, String.valueOf(device.createBond()));
        Log.d(TAG, "Trying to create a new connection.");
        mBluetoothDeviceAddress = address;
        mConnectionState = STATE_CONNECTING;
        return true;
    }

    /**
     * Disconnects an existing connection or cancel a pending connection. The disconnection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public void disconnect() {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.disconnect();
    }

    /**
     * After using a given BLE device, the app must call this method to ensure resources are
     * released properly.
     */
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

    /**
     * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
     * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
     * callback.
     *
     * @param characteristic The characteristic to read from.
     */
    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }


    /**
     * Retrieves a list of supported GATT services on the connected device. This should be
     * invoked only after {@code BluetoothGatt#discoverServices()} completes successfully.
     *
     * @return A {@code List} of supported services.
     */
    public List<BluetoothGattService> getSupportedGattServices() {
        if (mBluetoothGatt == null) return null;

        return mBluetoothGatt.getServices();
    }

    public BluetoothGatt getBluetoothGatt() {
        return mBluetoothGatt;
    }


    @Deprecated
    public void writeCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (this.mBluetoothAdapter == null || this.mBluetoothGatt == null) {
        } else {
            this.mBluetoothGatt.writeCharacteristic(characteristic);
        }
    }

    @Deprecated
    public boolean writeRXCharacteristic(String serviceUUID, String charactersticUUID, byte[] value) {
        BluetoothGattService RxService = null;
        if (this.mBluetoothGatt != null) {
            try {
                RxService = this.mBluetoothGatt.getService(UUID.fromString(serviceUUID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (RxService == null) {
            return false;
        }
        BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(UUID.fromString(charactersticUUID));
        if (RxChar == null) {
            return false;
        }
        RxChar.setValue(value);
        return this.mBluetoothGatt.writeCharacteristic(RxChar);
    }

    @Deprecated
    public boolean writeRXCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] value) {
        BluetoothGattService RxService = null;
        if (this.mBluetoothGatt == null) {
            return false;
        }
        bluetoothGattCharacteristic.setValue(value);
        return this.mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    @Deprecated
    public boolean writeUDCharacteristic(String serviceUUID, String charactersticUUID, byte[] value) {
        BluetoothGattService RxService = null;
        try {
            RxService = this.mBluetoothGatt.getService(UUID.fromString(serviceUUID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (RxService == null) {
            return false;
        }
        BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(UUID.fromString(charactersticUUID));
        if (RxChar == null) {
            return false;
        }
        RxChar.setValue(value);
        RxChar.setWriteType(1);
        boolean status = this.mBluetoothGatt.writeCharacteristic(RxChar);
        return status;
    }

    public boolean writeRXCharacteristic(byte[] value) {
        if (mBluetoothGatt != null) {
            BluetoothGattService RxService = this.mBluetoothGatt.getService(RX_SERVICE_UUID);
            if (RxService == null) {
//            showMessage("Rx service not found!");
//            broadcastUpdate(BroadcastCommand.DEVICE_DOES_NOT_SUPPORT_UART);
                return false;
            }
            BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(RX_CHAR_UUID);
            if (RxChar == null) {
//            showMessage("Rx charateristic not found!");
//            broadcastUpdate(BroadcastCommand.DEVICE_DOES_NOT_SUPPORT_UART);
                return false;
            }
            RxChar.setValue(value);
            boolean status = this.mBluetoothGatt.writeCharacteristic(RxChar);
            mBluetoothGatt.setCharacteristicNotification(RxChar, true);
            Log.d("lq", "write TXchar - status=" + status);
            return status;
        }
        return false;
    }

    public boolean setCharacteristicNotification(String serviceUUID, String characteristicUUID, boolean enabled) {
        if (this.mBluetoothAdapter == null || this.mBluetoothGatt == null) {
            return false;
        }
        BluetoothGattService RxService = this.mBluetoothGatt.getService(UUID.fromString(serviceUUID));
        if (RxService == null) {
            return false;
        }
        BluetoothGattCharacteristic TxChar = RxService.getCharacteristic(UUID.fromString(characteristicUUID));
        if (TxChar == null) {
            return false;
        }
        boolean status = this.mBluetoothGatt.setCharacteristicNotification(TxChar, enabled);
        return status;
    }

    /**
     * Enables or disables notification on a give characteristic.
     *
     * @param characteristic Characteristic to act on.
     * @param enabled        If true, enable notification.  False otherwise.
     */
    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic,
                                                 boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return false;
        }
        // This is specific to Heart Rate Measurement.
//        if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
//            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(
//                    UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
//            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
//            mBluetoothGatt.writeDescriptor(descriptor);
//        }

//        if (UUID_CHAR10.equals(characteristic.getUuid())) {
//            List<BluetoothGattDescriptor> bluetoothGattDescriptors = characteristic.getDescriptors();
//            if (bluetoothGattDescriptors.size() > 0) {
//                BluetoothGattDescriptor descriptor = bluetoothGattDescriptors.get(0);
//                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
//                mBluetoothGatt.writeDescriptor(descriptor);
//            }
//        }

        return mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
    }

    public BluetoothGattCharacteristic getBluetoothGattCharacteristic(String serviceUUID, String charUUID) {
        if (mBluetoothGatt == null) {
            return null;
        }
        return mBluetoothGatt.getService(UUID.fromString(serviceUUID)).getCharacteristic(UUID.fromString(charUUID));
    }
}
