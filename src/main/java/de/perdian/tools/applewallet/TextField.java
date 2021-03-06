package de.perdian.tools.applewallet;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextField extends Field<String> {

    static final long serialVersionUID = 1L;

    private List<DataDetectorType> dataDetectorTypes = null;

    public TextField() {
    }

    public TextField(String key, String label, String value) {
        this.setKey(key);
        this.setLabel(label);
        this.setValue(value);
    }

    @Override
    protected JsonObject toJsonObject() {
        if (this.getValue() == null || this.getValue().isEmpty()) {
            throw new IllegalArgumentException("Property 'value' must not be null!");
        } else {
            JsonObject jsonObject = super.toJsonObject();
            jsonObject.addProperty("value", this.getValue());
            jsonObject.addProperty("attributedValue", this.getAttributedValue());
            if (this.getDataDetectorTypes() != null && !this.getDataDetectorTypes().isEmpty()) {
                JsonArray dataDetectorTypes = new JsonArray();
                this.getDataDetectorTypes().stream().map(DataDetectorType::getValue).forEach(dataDetectorTypes::add);
                jsonObject.add("dataDetectorTypes", dataDetectorTypes);
            }
            return jsonObject;
        }
    }

    public List<DataDetectorType> getDataDetectorTypes() {
        return this.dataDetectorTypes;
    }
    public void setDataDetectorTypes(List<DataDetectorType> dataDetectorTypes) {
        this.dataDetectorTypes = dataDetectorTypes;
    }

}
