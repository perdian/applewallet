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

    public static Image from(DataSource dataSource) {
        return Image.from(dataSource, null);
    }

    public static Image from(DataSource dataSource, DataSource retinaDataSource) {
        Image image = new Image();
        image.setData(Image.bytesFromDataSource(dataSource));
        image.setRetinaData(Image.bytesFromDataSource(retinaDataSource));
        return image;
    }

    private static byte[] bytesFromDataSource(DataSource dataSource) {
        if (dataSource == null) {
            return null;
        } else {
            try (InputStream dataInStream = new BufferedInputStream(dataSource.getInputStream())) {
                try (ByteArrayOutputStream dataOutStream = new ByteArrayOutputStream()) {
                    for (int data = dataInStream.read(); data > -1; data = dataInStream.read()) {
                        dataOutStream.write(data);
                    }
                    dataOutStream.flush();
                    return dataOutStream.toByteArray();
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Cannot load image content from data source at: " + dataSource, e);
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
