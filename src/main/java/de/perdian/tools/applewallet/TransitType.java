package de.perdian.tools.applewallet;

/**
 * Represents the available transit types according to the Passbook API
 *
 * @author Christian Robert
 */

public enum TransitType {

    AIR("PKTransitTypeAir"),
    BOAT("PKTransitTypeBoat"),
    BUS("PKTransitTypeBus"),
    GENERIC("PKTransitTypeGeneric"),
    TRAIN("PKTransitTypeTrain");

    private String value = null;

    private TransitType(String value) {
        this.setValue(value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public String getValue() {
        return this.value;
    }
    private void setValue(String value) {
        this.value = value;
    }

}