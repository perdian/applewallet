package de.perdian.tools.applewallettools;

import java.time.Instant;

import com.google.gson.JsonObject;

public class DateField extends Field<Instant> {

    static final long serialVersionUID = 1L;

    private DateStyle dateStyle = DateStyle.SHORT;
    private Boolean ignoresTimeZone = null;
    private Boolean isRelative = null;
    private DateStyle timeStyle = DateStyle.SHORT;

    public DateField() {
    }

    public DateField(Instant value) {
        this.setValue(value);
    }

    @Override
    protected JsonObject toJsonObject() {
        if (this.getDateStyle() == null) {
            throw new IllegalArgumentException("Property 'dateStyle' must not be null!");
        } else if (this.getTimeStyle() == null) {
            throw new IllegalArgumentException("Property 'timeStyle' must not be null!");
        } else {
            JsonObject jsonObject = super.toJsonObject();
            jsonObject.addProperty("value", this.getValue().toString());
            jsonObject.addProperty("attributedValue", this.getAttributedValue().toString());
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
