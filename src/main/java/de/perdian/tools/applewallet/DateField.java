package de.perdian.tools.applewallet;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

public class DateField extends Field<Instant> {

    static final long serialVersionUID = 1L;

    private DateStyle dateStyle = DateStyle.SHORT;
    private Boolean ignoresTimeZone = null;
    private Boolean isRelative = null;
    private DateStyle timeStyle = DateStyle.SHORT;

    public DateField() {
    }

    public DateField(String key, String label, Instant value) {
        this.setKey(key);
        this.setLabel(label);
        this.setValue(value);
    }

    public DateField(String key, String label, Instant value, DateStyle dateStyle, DateStyle timeStyle) {
        this(key, label, value);
        this.setDateStyle(dateStyle);
        this.setTimeStyle(timeStyle);
    }

    @Override
    protected JsonObject toJsonObject() {
        if (this.getDateStyle() == null) {
            throw new IllegalArgumentException("Property 'dateStyle' must not be null!");
        } else if (this.getTimeStyle() == null) {
            throw new IllegalArgumentException("Property 'timeStyle' must not be null!");
        } else {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
            ZonedDateTime value = this.getValue() == null ? null : this.getValue().atZone(ZoneId.of("UTC"));
            ZonedDateTime attributedValue = this.getAttributedValue() == null ? null : this.getAttributedValue().atZone(ZoneId.of("UTC"));

            JsonObject jsonObject = super.toJsonObject();
            jsonObject.addProperty("value", value == null ? null : dateTimeFormatter.format(value));
            jsonObject.addProperty("attributedValue", attributedValue == null ? null : dateTimeFormatter.format(attributedValue));
            jsonObject.addProperty("dateStyle", this.getDateStyle() == null ? null : this.getDateStyle().getValue());
            jsonObject.addProperty("timeStyle", this.getTimeStyle() == null ? null : this.getTimeStyle().getValue());
            jsonObject.addProperty("ignoresTimeZone", this.getIgnoresTimeZone());
            jsonObject.addProperty("isRelative", this.getIsRelative());
            return jsonObject;

        }
    }

    public DateStyle getDateStyle() {
        return this.dateStyle;
    }
    public void setDateStyle(DateStyle dateStyle) {
        this.dateStyle = dateStyle;
    }

    public Boolean getIgnoresTimeZone() {
        return this.ignoresTimeZone;
    }
    public void setIgnoresTimeZone(Boolean ignoresTimeZone) {
        this.ignoresTimeZone = ignoresTimeZone;
    }

    public Boolean getIsRelative() {
        return this.isRelative;
    }
    public void setIsRelative(Boolean isRelative) {
        this.isRelative = isRelative;
    }

    public DateStyle getTimeStyle() {
        return this.timeStyle;
    }
    public void setTimeStyle(DateStyle timeStyle) {
        this.timeStyle = timeStyle;
    }

}
