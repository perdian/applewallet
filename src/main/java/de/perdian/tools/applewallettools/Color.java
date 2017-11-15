package de.perdian.tools.applewallettools;

import java.io.Serializable;

public class Color implements Serializable {

    static final long serialVersionUID = 1L;

    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public Color(int red, int green, int blue) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException("Invalid integer value for red: " + red);
        } else if (green < 0 || green > 255) {
            throw new IllegalArgumentException("Invalid integer value for green: " + green);
        } else if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Invalid integer value for blue: " + blue);
        } else {
            this.setRed(red);
            this.setGreen(green);
            this.setBlue(blue);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append("rgb(").append(this.getRed()).append(", ").append(this.getGreen()).append(", ").append(this.getBlue()).append(")").toString();
    }

    public int getRed() {
        return this.red;
    }
    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return this.green;
    }
    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return this.blue;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }

}
