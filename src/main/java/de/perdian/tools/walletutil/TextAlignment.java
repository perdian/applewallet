package de.perdian.tools.walletutil;

public enum TextAlignment {

    LEFT("PKTextAlignmentLeft"),
    CENTER("PKTextAlignmentCenter"),
    RIGHT("PKTextAlignmentRight"),
    GENERIC("PKTextAlignmentGeneric"),
    NATURAL("PKTextAlignmentNatural");

    private String value = null;

    private TextAlignment(String value) {
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