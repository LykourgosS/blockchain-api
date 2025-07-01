package com.lykourgoss.blockchainapi.reflection;

import com.lykourgoss.blockchainapi.reflection.FieldGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public enum Comparator {

    INSTANCE;

    public boolean compareCommonFieldsExcludingDefaultValues(Object o1, Object o2){
        Map<String, Object> fields1 = FieldGetter.INSTANCE.getAllFieldNamesValuesExcludingDefaultValues(o1);
        Map<String, Object> fields2 = FieldGetter.INSTANCE.getAllFieldNamesValuesExcludingDefaultValues(o2);
        List<String> commonInitializedFields = new ArrayList<>(fields1.keySet());
        commonInitializedFields.retainAll(fields2.keySet());
        for (String fieldName : commonInitializedFields) {
            Object value1 = fields1.get(fieldName);
            Object value2 = fields2.get(fieldName);
            if (!Objects.equals(value1, value2)){
                return false;
            }
        }
        return true;
    }
}
