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

    public boolean hasDefaultValue(Object object) {
        if (object == null){
            return true;
        }else {
            if (isNumber(object)) {
                return ((Number) object).doubleValue() == 0;
            } else if (isBoolean(object)) {
                return Boolean.FALSE.equals(object);
            } else if (isChar(object)) {
                return (Character) object == 0;
            } else if(isString(object)){
                return object.equals("");
            }
            return false;
        }
    }
}
