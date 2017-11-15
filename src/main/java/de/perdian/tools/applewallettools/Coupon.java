package de.perdian.tools.applewallettools;

import com.google.gson.JsonObject;

public class Coupon extends Pass {

    static final long serialVersionUID = 1L;

    private Image strip = null;

    @Override
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        JsonObject couponPassStyleObject = new JsonObject();
        couponPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
        couponPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
        couponPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
        couponPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
        couponPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
        jsonObject.add("coupon", couponPassStyleObject);

        return jsonObject;
    }

    public Image getStrip() {
        return this.strip;
    }
    public void setStrip(Image strip) {
        this.strip = strip;
    }

}
