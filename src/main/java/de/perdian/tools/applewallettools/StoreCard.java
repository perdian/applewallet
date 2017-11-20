package de.perdian.tools.applewallettools;

import java.util.Map;

import com.google.gson.JsonObject;

public class StoreCard extends Pass {

    static final long serialVersionUID = 1L;

    private Field<?> primaryField = null;
    private Image strip = null;

    @Override
    protected JsonObject toJsonObject() {
        if (this.getPrimaryField() == null) {
            throw new IllegalArgumentException("Property 'primaryField' must not be null");
        } else {
            JsonObject jsonObject = super.toJsonObject();
            JsonObject storeCardPassStyleObject = new JsonObject();
            storeCardPassStyleObject.add("primaryFields", PassHelper.toJsonArraySingleton(this.getPrimaryField(), Field::toJsonObject));
            storeCardPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
            storeCardPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
            storeCardPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
            storeCardPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
            jsonObject.add("storeCard", storeCardPassStyleObject);
            return jsonObject;
        }
    }

    @Override
    protected Map<String, Image> toImages() {
        Map<String, Image> images = super.toImages();
        images.put("strip", this.getStrip());
        return images;
    }

    public Field<?> getPrimaryField() {
        return this.primaryField;
    }
    public void setPrimaryField(Field<?> primaryField) {
        this.primaryField = primaryField;
    }

    public Image getStrip() {
        return this.strip;
    }
    public void setStrip(Image strip) {
        this.strip = strip;
    }

}
