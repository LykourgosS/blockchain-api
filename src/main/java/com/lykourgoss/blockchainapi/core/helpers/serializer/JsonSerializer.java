package com.lykourgoss.blockchainapi.core.helpers.serializer;

import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;

public class JsonSerializer implements Serializer<Object>{

    private final Class<?> clazz;

    public JsonSerializer(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String serialize(Object object) {
        return GsonJsonizer.INSTANCE.toJson(object);
    }

    @Override
    public Object deserialize(String string) {
        return GsonJsonizer.INSTANCE.fromJson(string, clazz);
    }
}
