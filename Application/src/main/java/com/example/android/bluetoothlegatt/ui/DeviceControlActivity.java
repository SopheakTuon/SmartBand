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

package com.example.android.bluetoothlegatt.ui;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.example.android.bluetoothlegatt.R;
import com.example.android.bluetoothlegatt.SampleGattAttributes;
import com.example.android.bluetoothlegatt.manager.CommandManager;
import com.example.android.bluetoothlegatt.models.BroadcastData;
import com.example.android.bluetoothlegatt.models.DataPacket;
import com.example.android.bluetoothlegatt.ble_service.BluetoothLeService;
import com.example.android.bluetoothlegatt.util.command.WriteCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity {
    private final static String TAG = DeviceControlActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private TextView mConnectionState;
    private TextView mDataField, tvTimer, mByteData;
    private String mDeviceName;
    private String mDeviceAddress;
    private ExpandableListView mGattServicesList;
    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    private Button buttonBP, buttonSP02, buttonHeart, buttonBreathRate;

    private boolean isMeasuring;

    private static int timeMeasure = 0;

    private Handler handler = new Handler();

    private boolean isHR = false;

    CommandManager mManager;


    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };


    List<Float> ecgDataAllList = new ArrayList<>();
    List<Float> pwDataAllList = new ArrayList<>();
    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
                enableElements(true);
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();
                enableElements(false);
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                BroadcastData broadcastData = (BroadcastData) intent.getSerializableExtra(BroadcastData.keyword);
                int commandID = broadcastData.commandID;
                if (commandID == 2) {
                    DataPacket dataPacket = (DataPacket) broadcastData.data;
                    ArrayList<Byte> datas = dataPacket.data;
                    ArrayList<Integer> data = new ArrayList();
                    for (int i = 0; i < datas.size(); i++) {
                        data.add(datas.get(i).byteValue() & 255);
                    }
                    if (data.get(0).intValue() == 49) {
                        if (data.get(1).intValue() == 10) {
                            displayByteDate(broadcastData);
                            if (data.get(2) > 0) {
                                displayData("HR : " + String.valueOf(data.get(2).intValue()));
                                stopMeasureHr();
                            }
                        }
                        if (data.get(1).intValue() == 34) {
                            displayByteDate(broadcastData);
                            if (data.get(2) > 0) {
                                displayData("BP High : " + String.valueOf(data.get(2).intValue()) + " (mmhg)" + "\nBP Low : " + String.valueOf(data.get(3).intValue()) + " (mmhg)");
                                stopMeasureBP();
                            }
                        }
                        if (data.get(1).intValue() == 18) {
                            displayByteDate(broadcastData);
                            if (data.get(2) > 0) {
                                displayData("SPO2 : " + String.valueOf(data.get(2).intValue()) + "%");
                                stopMeasureSPO2();
                            }
                        }
                    }
                }
            }
        }


    };

    private void displayByteDate(BroadcastData broadcastData) {
        mByteData.setText(bytesToByteString(broadcastData.getReceives()));
    }

    private void displayByteDate(String data) {
        mByteData.setText(data);
    }

    private String bytesToByteString(byte[] bytes) {
        String btyesString = "";
        for (int i = 0; i < bytes.length; i++) {
            btyesString += " " + bytes[i];
        }
        return btyesString;
    }


    // If a given GATT characteristic is selected, check for supported features.  This sample
    // demonstrates 'Read' and 'Notify' features.  See
    // http://d.android.com/reference/android/bluetooth/BluetoothGatt.html for the complete
    // list of supported characteristic features.
    private final ExpandableListView.OnChildClickListener servicesListClickListner =
            new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                            int childPosition, long id) {
                    if (mGattCharacteristics != null) {
                        final BluetoothGattCharacteristic characteristic =
                                mGattCharacteristics.get(groupPosition).get(childPosition);
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            // If there is an active notification on a characteristic, clear
                            // it first so it doesn't update the data field on the user interface.
                            if (mNotifyCharacteristic != null) {
                                mBluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, false);
                                mNotifyCharacteristic = null;
                            }
                            mBluetoothLeService.readCharacteristic(characteristic);
                        }
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            mNotifyCharacteristic = characteristic;
                            mBluetoothLeService.setCharacteristicNotification(characteristic, true);
                        }
                        return true;
                    }
                    return false;
                }
            };

    private void clearUI() {
        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
        mDataField.setText(R.string.no_data);
        mByteData.setText(R.string.no_data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatt_services_characteristics);
        mManager = CommandManager.getInstance(this);
        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        // Sets up UI references.
        ((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);
        mGattServicesList = (ExpandableListView) findViewById(R.id.gatt_services_list);
        mGattServicesList.setOnChildClickListener(servicesListClickListner);
        mConnectionState = (TextView) findViewById(R.id.connection_state);
        mDataField = (TextView) findViewById(R.id.data_value);
        mByteData = (TextView) findViewById(R.id.byte_data_value);
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        getActionBar().setTitle(mDeviceName);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

        init();
        initEventListener();


    }

    private void enableElements(final boolean enable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonBP.setEnabled(enable);
                buttonSP02.setEnabled(enable);
                buttonHeart.setEnabled(enable);
                buttonBreathRate.setEnabled(enable);
            }
        });
    }


    private void init() {
        buttonBP = (Button) findViewById(R.id.buttonBP);
        buttonSP02 = (Button) findViewById(R.id.buttonSPO2);
        buttonHeart = (Button) findViewById(R.id.buttonHeart);
        buttonBreathRate = (Button) findViewById(R.id.buttonBreathRate);
        enableElements(false);
    }

    private void initEventListener() {
        buttonBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMeasureBP();
                enableElements(false);
            }
        });

        buttonSP02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMeasureSPO2();
                enableElements(false);
            }
        });
        buttonHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMeasureHr();
                enableElements(false);
            }
        });
        buttonBreathRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unBindDevice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mManager.realTimeAndOnceMeasure(10, 0);
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gatt_services, menu);
        if (mConnected) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(resourceId);
            }
        });
    }


    private void displayTimer(final String data) {
        if (data != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvTimer.setText(data);
                }
            });
        }
    }

    private void displayData(final String data) {
        if (data != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDataField.setText(data);
                }
            });
        }
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) {
            return;
        }
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<>();
        mGattCharacteristics = new ArrayList<>();
//        String allServiceUUID = "";
        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<>();
            uuid = gattService.getUuid().toString();
//            Log.d("Service", "UUID : " + uuid + "\n" + "CMD : " + gattService.getType() + "\n" + "InstanceId : " + gattService.getInstanceId());
//            allServiceUUID += uuid + "\n";
            currentServiceData.put(LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<>();

//            String stringChar = "Service UUID  = " + uuid + "\n";
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<>();
                uuid = gattCharacteristic.getUuid().toString();
//                stringChar += uuid + "\n";
                currentCharaData.put(LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
//            Log.d("UUID", stringChar);
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(
                this,
                gattServiceData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2},
                gattCharacteristicData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        mGattServicesList.setAdapter(gattServiceAdapter);
//        mManager.setTimeSync();
    }

    /**
     * @return return 1 : - 1
     */
    private int unBindDevice() throws Exception {
        return WriteCommand.unbindDevice(mBluetoothLeService);
    }


    private void startMeasureHr() {
        isHR = true;
        displayByteDate("Collecting data...");
        displayData("Collecting data...");
        enableElements(false);
        mManager.realTimeAndOnceMeasure(10, 1);
        isMeasuring = true;
    }

    private void stopMeasureHr() {
        mManager.realTimeAndOnceMeasure(10, 0);
        enableElements(true);
    }

    private void startMeasureBP() {
        displayByteDate("Collecting data...");
        displayData("Collecting data...");
        enableElements(false);
        mManager.realTimeAndOnceMeasure(34, 1);
    }

    private void stopMeasureBP() {
        mManager.realTimeAndOnceMeasure(34, 0);
        enableElements(true);
    }

    private void startMeasureSPO2() {
        displayByteDate("Collecting data...");
        displayData("Collecting data...");
        enableElements(false);
        mManager.realTimeAndOnceMeasure(18, 1);
    }

    private void stopMeasureSPO2() {
        mManager.realTimeAndOnceMeasure(18, 0);
        enableElements(true);
    }

    /**
     * Stop measure
     *
     * @return
     */
    private int stopMeasure() {
        timeMeasure = 0;
        isMeasuring = false;
        enableElements(true);
        int result = WriteCommand.stopMeasuring(mBluetoothLeService);
        /**
         * Turn off notification of PW
         */

        return result;
    }

    /**
     * Stop Measure PW
     *
     * @return return 1 : -1
     */
    private int stopMeasurePW() {
        /**
         * Turn off notification of PW
         */
        BluetoothGattCharacteristic bluetoothGattCharacteristic = mBluetoothLeService.getBluetoothGatt().getService(UUID.fromString("0aabcdef-1111-2222-0000-facebeadaaaa")).getCharacteristic(UUID.fromString("facebead-ffff-eeee-0002-facebeadaaaa"));
        mBluetoothLeService.getBluetoothGatt().setCharacteristicNotification(bluetoothGattCharacteristic, false);
        BluetoothGattService bluetoothGattService = mBluetoothLeService.getBluetoothGatt().getService(UUID.fromString("0aabcdef-1111-2222-0000-facebeadaaaa"));
        BluetoothGattCharacteristic bluetoothGattCharacteristic1 = bluetoothGattService.getCharacteristic(UUID.fromString("facebead-ffff-eeee-0005-facebeadaaaa"));
        mBluetoothLeService.getBluetoothGatt().setCharacteristicNotification(bluetoothGattCharacteristic1, false);
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattService.getCharacteristic(UUID.fromString("ffacebead-ffff-eeee-0004-facebeadaaaa"));
        mBluetoothLeService.getBluetoothGatt().setCharacteristicNotification(bluetoothGattCharacteristic2, false);
        return stopMeasure();
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

}
