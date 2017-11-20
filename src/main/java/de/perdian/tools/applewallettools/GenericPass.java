package de.perdian.tools.applewallettools;

import java.util.Map;

import com.google.gson.JsonObject;

public class GenericPass extends Pass {

    static final long serialVersionUID = 1L;

    private Field<?> primaryField = null;
    private Image thumbnail = null;

    @Override
    protected JsonObject toJsonObject() {
        if (this.getPrimaryField() == null) {
            throw new IllegalArgumentException("Property 'primaryField' must not be null");
        } else {
            JsonObject jsonObject = super.toJsonObject();
            JsonObject genericPassStyleObject = new JsonObject();
            genericPassStyleObject.add("primaryFields", PassHelper.toJsonArraySingleton(this.getPrimaryField(), Field::toJsonObject));
            genericPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
            genericPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
            genericPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
            genericPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
            jsonObject.add("generic", genericPassStyleObject);
            return jsonObject;
        }
    }

    @Override
    protected Map<String, Image> toImages() {
        Map<String, Image> images = super.toImages();
        images.put("thumbnail", this.getThumbnail());
        return images;
    }

    public Field<?> getPrimaryField() {
        return this.primaryField;
    }
    public void setPrimaryField(Field<?> primaryField) {
        this.primaryField = primaryField;
    }

    public Image getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

}
