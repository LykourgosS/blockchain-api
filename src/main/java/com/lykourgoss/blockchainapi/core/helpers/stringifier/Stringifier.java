package com.lykourgoss.blockchainapi.core.helpers.stringifier;

import java.lang.reflect.Field;

public final class Stringifier {
    public static synchronized String toHexString(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }

    private static synchronized String toString(Object object, String fieldSeparator){
        StringBuilder builder = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()){
            if (field.getAnnotation(Exclude.class) != null){
                continue;
            }
            field.setAccessible(true);
            String fieldToString;
            try {
                if (field.getType().isPrimitive() || String.class.isAssignableFrom(field.getType())){
                    fieldToString =field.get(object).toString();
                } else{
                    fieldToString = Stringifier.toString(field.get(object), fieldSeparator);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            builder.append(fieldToString);
            builder.append(fieldSeparator);
        }
        return builder.toString();
    }

    public static synchronized String toString(Object object){
        return toString(object, "");
    }
}
