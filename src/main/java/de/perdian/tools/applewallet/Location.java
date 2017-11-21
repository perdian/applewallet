package de.perdian.tools.applewallet;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class Location implements Serializable {

    static final long serialVersionUID = 1L;

    private Double altitude = null;
    private Double latitude = null;
    private Double longitude = null;
    private String relevantText = null;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this(null, latitude, longitude, null);
    }

    public Location(Double altitude, Double latitude, Double longitude, String relevantText) {
        this.setAltitude(altitude);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setRelevantText(relevantText);
    }

    protected JsonObject toJsonObject() {
        if (this.getLatitude() == null) {
            throw new IllegalArgumentException("Property 'latitude' must not be empty!");
        } else if (this.getLongitude() == null) {
            throw new IllegalArgumentException("Property 'longitude' must not be empty!");
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("altitude", this.getAltitude());
            jsonObject.addProperty("latitude", this.getLatitude());
            jsonObject.addProperty("longitude", this.getLongitude());
            jsonObject.addProperty("relevantText", this.getRelevantText());
            return jsonObject;
        }
    }

    public Double getAltitude() {
        return this.altitude;
    }
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRelevantText() {
        return this.relevantText;
    }
    public void setRelevantText(String relevantText) {
        this.relevantText = relevantText;
    }

}
