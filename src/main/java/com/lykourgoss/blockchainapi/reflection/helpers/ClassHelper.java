package com.lykourgoss.blockchainapi.reflection.helpers;

import com.google.gson.reflect.TypeToken;
import com.lykourgoss.blockchainapi.reflection.TypeIdentifier;

import java.lang.reflect.Type;

public enum ClassHelper implements TypeIdentifier<Class<?>> {
    INSTANCE;

    public <T> Class<?> getClassOf(){
        return new TypeToken<T>(){}.getType().getClass();
    }

    @Override
    public boolean isNumber(Class<?> aClass) {
        return Number.class.isAssignableFrom(aClass)
                || aClass == byte.class
                || aClass == short.class
                || aClass == int.class
                || aClass == long.class
                || aClass == float.class
                || aClass == double.class;
    }

    @Override
    public boolean isBoolean(Class<?> aClass) {
        return aClass == boolean.class || Boolean.class.isAssignableFrom(aClass);
    }

    @Override
    public boolean isChar(Class<?> aClass) {
        return aClass == char.class || Character.class.isAssignableFrom(aClass);
    }

    @Override
    public boolean isString(Class<?> aClass) {
        return String.class.isAssignableFrom(aClass);
    }
}
