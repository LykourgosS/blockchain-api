package com.lykourgoss.blockchainapi.reflection;

public interface TypeIdentifier<T> {
    boolean isNumber(T t);
    boolean isBoolean(T t);
    boolean isChar(T t);
    boolean isString(T t);
}
