package org.example.model;

import java.util.List;

public record ParsedCronField(FieldTimeType fieldTimeType, List<String> values) {

    @Override
    public String toString() {
        var concatenatedValues = String.join(" ", values);
        return fieldTimeType + " " + concatenatedValues;
    }
}
