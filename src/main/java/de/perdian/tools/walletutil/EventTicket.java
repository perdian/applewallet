package de.perdian.tools.walletutil;

import com.google.gson.JsonObject;

public class EventTicket extends Pass {

    static final long serialVersionUID = 1L;

    private String groupingIdentifier = null;

    @Override
    protected JsonObject toJsonObject() {
        throw new UnsupportedOperationException();
    }

    public String getGroupingIdentifier() {
        return this.groupingIdentifier;
    }
    public void setGroupingIdentifier(String groupingIdentifier) {
        this.groupingIdentifier = groupingIdentifier;
    }

}
