package de.perdian.tools.applewallettools;

import com.google.gson.JsonObject;

public class StoreCard extends Pass {

    static final long serialVersionUID = 1L;

    private Image strip = null;

    @Override
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        JsonObject storeCardPassStyleObject = new JsonObject();
        storeCardPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
        storeCardPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
        storeCardPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
        storeCardPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
        storeCardPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
        jsonObject.add("storeCard", storeCardPassStyleObject);

        return jsonObject;
    }

    public Image getStrip() {
        return this.strip;
    }
    public void setStrip(Image strip) {
        this.strip = strip;
    }

}
