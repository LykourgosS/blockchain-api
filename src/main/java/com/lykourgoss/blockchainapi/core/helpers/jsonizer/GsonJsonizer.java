package com.lykourgoss.blockchainapi.core.helpers.jsonizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public enum GsonJsonizer implements Jsonizer {
    INSTANCE;

    private final Gson gson;
    private final Gson prettyGson;

    GsonJsonizer() {
        this.gson = new Gson();
        this.prettyGson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public String toPrettyJson(Object object) {
        return prettyGson.toJson(object);
    }

    @Override
    public <T> T fromJson(String string) {
        return null;
    }

    @Override
    public <T> T fromJson(String string, Type type) {
        return gson.fromJson(string, type);
    }

    @Override
    public <T> T fromJson(String string, Class<T> tClass) {
        return gson.fromJson(string, tClass);
    }
}
