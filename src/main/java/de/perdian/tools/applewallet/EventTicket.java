package de.perdian.tools.applewallet;

import java.util.Map;

import com.google.gson.JsonObject;

public class EventTicket extends Pass {

    static final long serialVersionUID = 1L;

    private Field<?> primaryField = null;
    private String groupingIdentifier = null;
    private Image strip = null;
    private Image background = null;
    private Image thumbnail = null;

    @Override
    protected JsonObject toJsonObject() {
        if (this.getPrimaryField() == null) {
            throw new IllegalArgumentException("Property 'primaryField' must not be null");
        } else {
            JsonObject jsonObject = super.toJsonObject();
            JsonObject eventTicketPassStyleObject = new JsonObject();
            eventTicketPassStyleObject.add("primaryFields", PassHelper.toJsonArraySingleton(this.getPrimaryField(), Field::toJsonObject));
            eventTicketPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
            eventTicketPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
            eventTicketPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
            eventTicketPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
            jsonObject.add("eventTicket", eventTicketPassStyleObject);
            jsonObject.addProperty("groupingIdentifier", this.getGroupingIdentifier());
            return jsonObject;
        }
    }

    @Override
    protected Map<String, Image> toImages() {

        if (this.getStrip() != null && !this.getStrip().isEmpty()) {
            if (this.getBackground() != null && !this.getBackground().isEmpty()) {
                throw new IllegalArgumentException("If a 'strip' has been specified 'background' must be empty!");
            } else if (this.getThumbnail() != null && !this.getThumbnail().isEmpty()) {
                throw new IllegalArgumentException("If a 'strip' has been specified 'background' must be empty!");
            }
        }

        Map<String, Image> images = super.toImages();
        images.put("strip", this.getStrip());
        images.put("background", this.getBackground());
        images.put("thumbnail", this.getThumbnail());
        return images;

    }


    public Field<?> getPrimaryField() {
        return this.primaryField;
    }
    public void setPrimaryField(Field<?> primaryField) {
        this.primaryField = primaryField;
    }

    public String getGroupingIdentifier() {
        return this.groupingIdentifier;
    }
    public void setGroupingIdentifier(String groupingIdentifier) {
        this.groupingIdentifier = groupingIdentifier;
    }

    public Image getStrip() {
        return this.strip;
    }
    public void setStrip(Image strip) {
        this.strip = strip;
    }

    public Image getBackground() {
        return this.background;
    }
    public void setBackground(Image background) {
        this.background = background;
    }

    public Image getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

}
