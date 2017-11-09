package de.perdian.tools.walletutil;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class Beacon implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer major = null;
    private Integer minor = null;
    private String proximityUUID = null;
    private String relevantText = null;

    public Beacon() {
    }

    public Beacon(String proximityUUID) {
        this(proximityUUID, null, null, null);
    }

    public Beacon(String proximityUUID, Integer major, Integer minor) {
        this(proximityUUID, major, minor, null);
    }

    public Beacon(String proximityUUID, Integer major, Integer minor, String relevantText) {
        this.setProximityUUID(proximityUUID);
        this.setMajor(major);
        this.setMinor(minor);
        this.setRelevantText(relevantText);
    }

    protected JsonObject toJsonObject() {
        if (this.getProximityUUID() == null || this.getProximityUUID().isEmpty()) {
            throw new IllegalArgumentException("Property 'proximityUUID' must not be empty!");
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("major", this.getMajor());
            jsonObject.addProperty("minor", this.getMinor());
            jsonObject.addProperty("proximityUUID", this.getProximityUUID());
            jsonObject.addProperty("relevantText", this.getRelevantText());
            return jsonObject;
        }
    }

    public Integer getMajor() {
        return this.major;
    }
    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getMinor() {
        return this.minor;
    }
    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    public String getProximityUUID() {
        return this.proximityUUID;
    }
    public void setProximityUUID(String proximityUUID) {
        this.proximityUUID = proximityUUID;
    }

    public String getRelevantText() {
        return this.relevantText;
    }
    public void setRelevantText(String relevantText) {
        this.relevantText = relevantText;
    }

}
