package com.example.android.bluetoothlegatt.models;

/**
 * @author Sopheak Tuon
 * @created on 29-May-17
 */

import java.io.Serializable;
import java.util.ArrayList;

public class DataPacket implements Serializable {
    private static final long serialVersionUID = 1;
    public int commandID;
    public ArrayList<Byte> data;
    public int length;

    public DataPacket(int id, int len) {
        this.data = new ArrayList();
        this.commandID = id;
        this.length = len;
    }

    public void add(byte b) {
        this.data.add(Byte.valueOf(b));
    }
}

