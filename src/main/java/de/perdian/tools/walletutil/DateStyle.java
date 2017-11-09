package de.perdian.tools.walletutil;

public enum DateStyle {

    NONE("PKDateStyleNone"),
    SHORT("PKDateStyleShort"),
    MEDIUM("PKDateStyleMedium"),
    LONG("PKDateStyleLong"),
    FULL("PKDateStyleFull");

    private String value = null;

    private DateStyle(String value) {
        this.setValue(value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public String getValue() {
        return this.value;
    }
    private void setValue(String value) {
        this.value = value;
    }

}