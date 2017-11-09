package de.perdian.tools.walletutil;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class Barcode implements Serializable {

    static final long serialVersionUID = 1L;

    private String altText = null;
    private BarcodeFormat format = null;
    private String message = null;
    private String messageEncoding = "ISO-8859-1";

    public Barcode() {
    }

    public Barcode(BarcodeFormat format, String message) {
        this(format, message, "ISO-8859-1");
    }

    public Barcode(BarcodeFormat format, String message, String messageEncoding) {
        this.setFormat(format);
        this.setMessage(message);
        this.setMessageEncoding(messageEncoding);
    }

    protected JsonObject toJsonObject() {
        if (this.getFormat() == null) {
            throw new IllegalArgumentException("Property 'format' must not be null!");
        } else if (this.getMessage() == null || this.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Property 'message' must not be empty!");
        } else if (this.getMessageEncoding() == null || this.getMessageEncoding().isEmpty()) {
            throw new IllegalArgumentException("Property 'messageEncoding' must not be empty!");
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("altText", this.getAltText());
            jsonObject.addProperty("format", this.getFormat().getValue());
            jsonObject.addProperty("message", this.getMessage());
            jsonObject.addProperty("messageEncoding", this.getMessageEncoding());
            return jsonObject;
        }
    }

    public String getAltText() {
        return this.altText;
    }
    public void setAltText(String altText) {
        this.altText = altText;
    }

    public BarcodeFormat getFormat() {
        return this.format;
    }
    public void setFormat(BarcodeFormat format) {
        this.format = format;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageEncoding() {
        return this.messageEncoding;
    }
    public void setMessageEncoding(String messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

}
