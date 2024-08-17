package com.lykourgoss.blockchainapi.reflection;

public enum ClassHelper implements TypeIdentifier<Class<?>> {
    INSTANCE;

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
