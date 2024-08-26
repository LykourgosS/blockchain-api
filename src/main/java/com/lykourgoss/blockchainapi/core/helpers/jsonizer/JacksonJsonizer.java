package com.lykourgoss.blockchainapi.core.helpers.jsonizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.Type;

public enum JacksonJsonizer implements Jsonizer {

    INSTANCE;

    private final ObjectMapper objectMapper;

    JacksonJsonizer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toPrettyJson(Object object) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <T> T fromJson(String string) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <T> T fromJson(String string, Type type) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <T> T fromJson(String string, Class<T> tClass) {
        try {
            return objectMapper.readValue(string, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getDeepCopy(Object t, Class<T> tClass) {
        return fromJson(toJson(t), tClass);
    }
}
