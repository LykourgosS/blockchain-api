package com.lykourgoss.blockchainapi.helpers.jsonizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
