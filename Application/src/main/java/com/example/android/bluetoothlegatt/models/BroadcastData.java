package com.example.android.bluetoothlegatt.models;

/**
 * @author Sopheak Tuon
 * @created on 29-May-17
 */


import java.io.Serializable;

public class BroadcastData implements Serializable {
    public static final String keyword = "command";
    private static final long serialVersionUID = 1;
    public int commandID;
    private byte[] receives;
    public Object data;

    public BroadcastData() {
        this.commandID = 0;
        this.data = null;
    }

    public BroadcastData(int id) {
        this.commandID = 0;
        this.data = null;
        this.commandID = id;
    }

    public BroadcastData(int id, Object obj) {
        this.commandID = 0;
        this.data = null;
        this.commandID = id;
        this.data = obj;
    }

    public byte[] getReceives() {
        return receives;
    }

    public void setReceives(byte[] receives) {
        this.receives = receives;
    }
}

