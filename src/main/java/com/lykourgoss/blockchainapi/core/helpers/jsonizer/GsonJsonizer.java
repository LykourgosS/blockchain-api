package com.lykourgoss.blockchainapi.core.helpers.jsonizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public enum GsonJsonizer implements Jsonizer {
    INSTANCE;

    private final Gson gson;

    GsonJsonizer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJson(String string) {
        return gson.fromJson(string, new TypeToken<T>(){}.getType());
    }
}
