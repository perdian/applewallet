package de.perdian.tools.applewallettools;

public enum DataDetectorType {

    PHONE_NUMBER("PKDataDetectorTypePhoneNumber"),
    LINK("PKDataDetectorTypeLink"),
    ADDRESS("PKDataDetectorTypeAddress"),
    CALENDAR_EVENT("PKDataDetectorTypeCalendarEvent");

    private String value = null;

    private DataDetectorType(String value) {
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