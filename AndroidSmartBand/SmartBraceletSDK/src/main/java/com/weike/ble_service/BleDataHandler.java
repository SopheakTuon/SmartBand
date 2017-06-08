/*******************************************************************
 * @file BleDataHandler.java
 * @par Copyright (C) 2014-2015 MegaChips Corporation
 * @date 2014/02/26
 *******************************************************************/

package com.weike.ble_service;

import java.io.Serializable;

import android.util.Log;

import com.weike.chiginon.DataPacket;


public class BleDataHandler implements Serializable {

    private static final long serialVersionUID = 1L;

    public String dialog_message = "";

    private DataPacket dataPacket;
    private int timeoutCounter = 0;
    private int packetCounter = 0;
    private static final int TIMEOUT_COUNT = 20;

    public static final int PACKET_COMPLETE = 0;
    public static final int PACKET_INCOMPLETE = 1;
    public static final int PACKET_DATA_OVERSIZE = 2;

    public BleDataHandler() {
    }


    /**
     * @brief Interval timer function.
     */
    private void timerTick() {
        timeoutCounter++;
        if (timeoutCounter > TIMEOUT_COUNT) {
            //timeout counter reaches TIMEOUT value.
            //clear_packet();
        }
        return;
    }


    /**
     * @brief Add data received from BLE interruption.
     */
    public boolean add_data(byte[] received) {//00 02 21 03 23 02

        timeoutCounter = 0;
        //                        手环2.0                         手环1.0
        if ((received[0] & 0Xff) == 0xAB || (received[0] & 0Xff) == 0x04) {//第一个包
            int id = (int) received[4];//获取id
            Log.d("lq","packetCounter:"+packetCounter);
            //获取数据的长�?
            int length = received.length-3;
            //里面封装了从蓝牙获取的数�?
            dataPacket = new DataPacket(id, length);
            for (int i = 4; i < received.length; i++) {
                //遍历数组，把每个字节添加到集�?
                dataPacket.add(received[i]);
            }
            return true;
        } else {
            //不是第一个包
            if (packetCounter == (int) received[0]) {
                packetCounter++;
                dataPacket = new DataPacket(received[0],received.length);
                Log.d("lq","packetCounter:"+packetCounter);
                //从第二位�?始加
                for (int i = 1; i < received.length; i++) {
                    dataPacket.add(received[i]);
                }
                return true;
            } else {
                clear_packet();
                return false;
            }
        }
    }

    /**
     * @brief Clear internal data buffer.
     */
    public void clear_packet() {
        dataPacket = null;
        packetCounter = 0;
    }


    /**
     * @brief Check received data packet completion.
     */
    public int check_packet() {

        if (dataPacket.length == dataPacket.data.size()+1) {
            return PACKET_COMPLETE;//0 添加完成
        } else if (dataPacket.length > dataPacket.data.size()+1) {
            return PACKET_INCOMPLETE;//1 正在添加
        } else {
            clear_packet();
            return PACKET_DATA_OVERSIZE;//2 添加失败
        }
    }


    /**
     * @brief Get data as DataPacket.
     */
    public DataPacket get_packet() {
        DataPacket temp_packet = dataPacket;
        clear_packet();
        return temp_packet;
    }

}
