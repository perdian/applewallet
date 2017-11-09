package de.perdian.tools.walletutil;

import com.google.gson.JsonObject;

public class GenericPass extends Pass {

    static final long serialVersionUID = 1L;

    @Override
    protected JsonObject toJsonObject() {
        throw new UnsupportedOperationException();
    }

}
