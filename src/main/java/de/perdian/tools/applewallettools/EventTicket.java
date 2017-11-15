package de.perdian.tools.applewallettools;

import com.google.gson.JsonObject;

public class EventTicket extends Pass {

    static final long serialVersionUID = 1L;

    private String groupingIdentifier = null;

    private Image strip = null;
    private Image background = null;
    private Image thumbnail = null;

    @Override
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        JsonObject eventTicketPassStyleObject = new JsonObject();
        eventTicketPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
        eventTicketPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
        eventTicketPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
        eventTicketPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
        eventTicketPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
        jsonObject.add("eventTicket", eventTicketPassStyleObject);
        jsonObject.addProperty("groupingIdentifier", this.getGroupingIdentifier());

        return jsonObject;
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
