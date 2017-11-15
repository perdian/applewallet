package de.perdian.tools.applewallettools;

import com.google.gson.JsonObject;

public class GenericPass extends Pass {

    static final long serialVersionUID = 1L;

    private Image thumbnail = null;

    @Override
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        JsonObject genericPassStyleObject = new JsonObject();
        genericPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
        genericPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
        genericPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
        genericPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
        genericPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
        jsonObject.add("generic", genericPassStyleObject);

        return jsonObject;
    }

    public Image getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

}
