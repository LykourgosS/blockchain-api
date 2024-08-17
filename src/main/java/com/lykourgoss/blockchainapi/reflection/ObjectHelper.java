package com.lykourgoss.blockchainapi.reflection;

public enum ObjectHelper implements TypeIdentifier<Object> {
    INSTANCE;
    @Override
    public boolean isNumber(Object object) {
        return ClassHelper.INSTANCE.isNumber(object.getClass());
    }

    @Override
    public boolean isBoolean(Object object) {
        return ClassHelper.INSTANCE.isBoolean(object.getClass());
    }

    @Override
    public boolean isChar(Object object) {
        return ClassHelper.INSTANCE.isChar(object.getClass());
    }

    @Override
    public boolean isString(Object object) {
        return ClassHelper.INSTANCE.isString(object.getClass());
    }
}
