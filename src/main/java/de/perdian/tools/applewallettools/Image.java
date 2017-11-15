package de.perdian.tools.applewallettools;

import java.io.Serializable;

public class Image implements Serializable {

    static final long serialVersionUID = 1L;

    private byte[] data = null;
    private byte[] retinaData = null;

    public byte[] getData() {
        return this.data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getRetinaData() {
        return this.retinaData;
    }
    public void setRetinaData(byte[] retinaData) {
        this.retinaData = retinaData;
    }

}
