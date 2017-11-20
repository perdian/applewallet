package de.perdian.tools.applewallettools;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.activation.DataSource;

public class Image implements Serializable {

    static final long serialVersionUID = 1L;

    private byte[] data = null;
    private byte[] retinaData = null;

    public Image() {
    }

    public Image(byte[] data) {
        this.setData(data);
    }

    public Image(byte[] data, byte[] retinaData) {
        this.setData(retinaData);
        this.setRetinaData(retinaData);
    }

    public Image(DataSource dataSource, DataSource retinaDataSource) throws IOException {
        if (dataSource != null) {
            try (InputStream dataInStream = new BufferedInputStream(dataSource.getInputStream())) {
                try (ByteArrayOutputStream dataOutStream = new ByteArrayOutputStream()) {
                    for (int data = dataInStream.read(); data > -1; data = dataInStream.read()) {
                        dataOutStream.write(data);
                    }
                    dataOutStream.flush();
                    this.setData(dataOutStream.toByteArray());
                }
            }
        }
        if (retinaDataSource != null) {
            try (InputStream retinaDataInStream = new BufferedInputStream(retinaDataSource.getInputStream())) {
                try (ByteArrayOutputStream retinaDataOutStream = new ByteArrayOutputStream()) {
                    for (int data = retinaDataInStream.read(); data > -1; data = retinaDataInStream.read()) {
                        retinaDataOutStream.write(data);
                    }
                    retinaDataOutStream.flush();
                    this.setRetinaData(retinaDataOutStream.toByteArray());
                }
            }
        }
    }

    public boolean isEmpty() {
        return this.getData() == null && this.getRetinaData() == null;
    }

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
