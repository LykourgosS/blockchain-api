package com.lykourgoss.blockchainapi.core.helpers.jsonizer;

import java.lang.reflect.Type;

public interface Jsonizer {
    String toJson(Object object);
    String toPrettyJson(Object object);
    <T> T fromJson(String string);
    <T> T fromJson(String string, Type type);
    <T> T fromJson(String string, Class<T> tClass);
    <T> T getDeepCopy(T t, Class<T> tClass);
}
