/*******************************************************************
 * @file MainMenu.java
 * @par Copyright (C) 2014-2015 MegaChips Corporation
 * @date 2014/02/26
 *******************************************************************/
package com.weike.chiginon;

import java.io.Serializable;
import java.util.ArrayList;

public class DataPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    public int commandID;//蓝牙指令id
    public int length;//数据长度
    public ArrayList<Byte> data = new ArrayList<Byte>();

    public DataPacket(int id, int len) {
        commandID = id;
        length = len;
    }

    //字节数组中的每个字节添加到集合
    public void add(byte b) {
        data.add(b);
    }

}
