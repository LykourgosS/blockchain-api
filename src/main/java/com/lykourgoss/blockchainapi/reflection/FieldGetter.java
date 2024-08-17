package com.lykourgoss.blockchainapi.reflection;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public enum FieldGetter {
    INSTANCE;

    public boolean hasDefaultValue(Field field, Object object) {
        Object value;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ObjectHelper.INSTANCE.hasDefaultValue(value);
    }

    public List<Field> getAllFields(Object object) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.stream(object.getClass().getDeclaredFields()).toList());
        fields.addAll(Arrays.stream(object.getClass().getFields()).toList());
        fields.forEach(x -> x.setAccessible(true));
        return fields;
    }

    public List<String> getAllFieldNames(Object object) {
        return getAllFields(object).stream().map(Field::getName).collect(Collectors.toList());
    }

    public List<String> getCommonFieldNames(Object o1, Object o2) {
        List<String> fields1 = getAllFieldNames(o1);
        List<String> fields2 = getAllFieldNames(o2);
        return fields1.stream().filter(fields2::contains).collect(Collectors.toList());
    }

    private Map<String, Object> toFieldNamesValues(List<Field> fields, Object object) {
        Map<String, Object> namesValues = new HashMap<>();
        fields.forEach(x -> {
            try {
                namesValues.put(x.getName(), x.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return namesValues;
    }

    public Map<String, Object> getAllFieldNamesValues(Object object) {
        return toFieldNamesValues(getAllFields(object), object);
    }

    public List<Field> getAllFieldsExcludingDefaultValues(Object object) {
        return getAllFields(object).stream().filter(x -> !hasDefaultValue(x, object)).collect(Collectors.toList());
    }

    public Map<String, Object> getAllFieldNamesValuesExcludingDefaultValues(Object object) {
        return toFieldNamesValues(getAllFieldsExcludingDefaultValues(object), object);
    }

    public boolean hasInitializedFields(Object object) {
        return !getAllFieldsExcludingDefaultValues(object).isEmpty();
    }
}
