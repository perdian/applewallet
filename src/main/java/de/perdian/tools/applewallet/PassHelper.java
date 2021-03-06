package de.perdian.tools.applewallet;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class PassHelper {

    static <T> JsonArray toJsonArraySingleton(T item, Function<T, JsonObject> detailObjectFunction) {
        return PassHelper.toJsonArray(Collections.singleton(item), detailObjectFunction);
    }

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
