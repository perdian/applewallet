package de.perdian.tools.walletutil;

import java.io.Serializable;

import com.google.gson.JsonObject;

public abstract class Field<T> implements Serializable {

    static final long serialVersionUID = 1L;

    private String key = null;
    private String label = null;
    private T value = null;
    private T attributedValue = null;
    private String changeMessage = null;

    protected JsonObject toJsonObject() {
        if (this.getKey() == null || this.getKey().isEmpty()) {
            throw new IllegalArgumentException("Property 'key' must not be empty!");
        } else if (this.getValue() == null) {
            throw new IllegalArgumentException("Property 'value' must not be null!");
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key", this.getKey());
            jsonObject.addProperty("label", this.getLabel());
            jsonObject.addProperty("changeMessage", this.getChangeMessage());
            return jsonObject;
        }
    }

    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public T getValue() {
        return this.value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public T getAttributedValue() {
        return this.attributedValue;
    }
    public void setAttributedValue(T attributedValue) {
        this.attributedValue = attributedValue;
    }

    public String getChangeMessage() {
        return this.changeMessage;
    }
    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

}
