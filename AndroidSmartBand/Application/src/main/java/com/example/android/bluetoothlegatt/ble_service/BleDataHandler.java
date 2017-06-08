package com.example.android.bluetoothlegatt.ble_service;

import android.util.Log;

import com.example.android.bluetoothlegatt.BuildConfig;
import com.example.android.bluetoothlegatt.models.DataPacket;

import java.io.Serializable;

public class BleDataHandler implements Serializable {
    public static final int PACKET_COMPLETE = 0;
    public static final int PACKET_DATA_OVERSIZE = 2;
    public static final int PACKET_INCOMPLETE = 1;
    private static final int TIMEOUT_COUNT = 20;
    private static final long serialVersionUID = 1;
    private DataPacket dataPacket;
    public String dialog_message;
    private int packetCounter;
    private int timeoutCounter;

    public BleDataHandler() {
        this.dialog_message = BuildConfig.VERSION_NAME;
        this.timeoutCounter = PACKET_COMPLETE;
        this.packetCounter = PACKET_COMPLETE;
    }

    private void timerTick() {
        this.timeoutCounter += PACKET_INCOMPLETE;
        if (this.timeoutCounter <= TIMEOUT_COUNT) {
        }
    }

    public boolean add_data(byte[] received) {
        this.timeoutCounter = PACKET_COMPLETE;
        int i;
        if ((received[PACKET_COMPLETE] & 255) == 171 || (received[PACKET_COMPLETE] & 255) == 4) {
            int id = received[4];
            Log.d("lq", "packetCounter:" + this.packetCounter);
            this.dataPacket = new DataPacket(id, received.length - 3);
            for (i = 4; i < received.length; i += PACKET_INCOMPLETE) {
                this.dataPacket.add(received[i]);
            }
            return true;
        } else if (this.packetCounter == received[PACKET_COMPLETE]) {
            this.packetCounter += PACKET_INCOMPLETE;
            this.dataPacket = new DataPacket(received[PACKET_COMPLETE], received.length);
            Log.d("lq", "packetCounter:" + this.packetCounter);
            for (i = PACKET_INCOMPLETE; i < received.length; i += PACKET_INCOMPLETE) {
                this.dataPacket.add(received[i]);
            }
            return true;
        } else {
            clear_packet();
            return false;
        }
    }

    public void clear_packet() {
        this.dataPacket = null;
        this.packetCounter = PACKET_COMPLETE;
    }

    public int check_packet() {
        if (this.dataPacket.length == this.dataPacket.data.size() + PACKET_INCOMPLETE) {
            return PACKET_COMPLETE;
        }
        if (this.dataPacket.length > this.dataPacket.data.size() + PACKET_INCOMPLETE) {
            return PACKET_INCOMPLETE;
        }
        clear_packet();
        return PACKET_DATA_OVERSIZE;
    }

    public DataPacket get_packet() {
        DataPacket temp_packet = this.dataPacket;
        clear_packet();
        return temp_packet;
    }
}
