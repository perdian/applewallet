package de.perdian.tools.walletutil;

public enum BarcodeFormat {

    QR("PKBarcodeFormatQR"),
    PDF417("PKBarcodeFormatPDF417"),
    AZTEC("PKBarcodeFormatAztec");

    private String value = null;

    private BarcodeFormat(String value) {
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