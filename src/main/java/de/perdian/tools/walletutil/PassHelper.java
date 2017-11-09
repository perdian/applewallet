package de.perdian.tools.walletutil;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class PassHelper {

    static <T> JsonArray toJsonArray(Collection<? extends T> items, Function<T, JsonObject> detailObjectFunction) {
        if (items == null || items.isEmpty()) {
            return null;
        } else {

            JsonArray jsonArray = new JsonArray();
            items.stream()
                .map(detailObjectFunction)
                .filter(Objects::nonNull)
                .forEach(jsonArray::add);

            return jsonArray;

        }
    }

}
