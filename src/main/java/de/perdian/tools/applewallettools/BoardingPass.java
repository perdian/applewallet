package de.perdian.tools.applewallettools;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.JsonObject;

public class BoardingPass extends Pass {

    static final long serialVersionUID = 1L;

    private List<Field<?>> primaryFields = null;
    private TransitType transitType = TransitType.GENERIC;
    private String groupingIdentifier = null;
    private Image footer = null;

    @Override
    protected JsonObject toJsonObject() {
        if (this.getPrimaryFields() == null || this.getPrimaryFields().isEmpty()) {
            throw new IllegalArgumentException("Property 'primaryFields' must not be empty!");
        } else if (this.getPrimaryFields().size() > 2) {
            throw new IllegalArgumentException("Property 'primaryFields' must have 2 entries at most! (Current size: " + this.getPrimaryFields().size() + ")");
        } else {

            JsonObject jsonObject = super.toJsonObject();
            JsonObject boardingPassPassStyleObject = new JsonObject();
            boardingPassPassStyleObject.add("primaryFields", PassHelper.toJsonArray(this.getPrimaryFields(), Field::toJsonObject));
            boardingPassPassStyleObject.add("auxiliaryFields", PassHelper.toJsonArray(this.getAuxiliaryFields(), Field::toJsonObject));
            boardingPassPassStyleObject.add("secondaryFields", PassHelper.toJsonArray(this.getSecondaryFields(), Field::toJsonObject));
            boardingPassPassStyleObject.add("backFields", PassHelper.toJsonArray(this.getBackFields(), Field::toJsonObject));
            boardingPassPassStyleObject.add("headerFields", PassHelper.toJsonArray(this.getHeaderFields(), Field::toJsonObject));
            boardingPassPassStyleObject.addProperty("transitType", Optional.ofNullable(this.getTransitType()).orElse(TransitType.GENERIC).getValue());
            jsonObject.add("boardingPass", boardingPassPassStyleObject);
            jsonObject.addProperty("groupingIdentifier", this.getGroupingIdentifier());
            return jsonObject;

        }
    }

    @Override
    protected Map<String, Image> toImages() {
        Map<String, Image> images = super.toImages();
        images.put("footer", this.getFooter());
        return images;
    }

    public List<Field<?>> getPrimaryFields() {
        return this.primaryFields;
    }
    public void setPrimaryFields(List<Field<?>> primaryFields) {
        this.primaryFields = primaryFields;
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

    public Image getFooter() {
        return this.footer;
    }
    public void setFooter(Image footer) {
        this.footer = footer;
    }

}
