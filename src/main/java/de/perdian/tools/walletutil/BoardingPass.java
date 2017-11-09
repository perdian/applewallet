package de.perdian.tools.walletutil;

import java.util.Optional;

import com.google.gson.JsonObject;

public class BoardingPass extends Pass {

    static final long serialVersionUID = 1L;

    private TransitType transitType = TransitType.GENERIC;
    private String groupingIdentifier = null;

    @Override
    protected JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        JsonObject boardingPassPassStyleObject = new JsonObject();
        boardingPassPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
        boardingPassPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
        boardingPassPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
        boardingPassPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
        boardingPassPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
        boardingPassPassStyleObject.addProperty("transitType", Optional.ofNullable(this.getTransitType()).orElse(TransitType.GENERIC).getValue());
        jsonObject.add("boardingPass", boardingPassPassStyleObject);
        jsonObject.addProperty("groupingIdentifier", this.getGroupingIdentifier());

        return jsonObject;
    }

    public TransitType getTransitType() {
        return this.transitType;
    }
    public void setTransitType(TransitType transitType) {
        this.transitType = transitType;
    }

    public String getGroupingIdentifier() {
        return this.groupingIdentifier;
    }
    public void setGroupingIdentifier(String groupingIdentifier) {
        this.groupingIdentifier = groupingIdentifier;
    }

}
