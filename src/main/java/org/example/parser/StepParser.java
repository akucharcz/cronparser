package org.example.parser;

import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.example.utility.RangeExpander;

import java.util.stream.Collectors;

public final class StepParser implements FieldParser {
    private static final String STEP = "/";

    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.STEP == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        int value = Integer.parseInt(field.split(STEP)[1]);
        var range = RangeExpander.expandRange(fieldTimeType);
        var values = range.stream()
                .mapToInt(Integer::valueOf)
                .filter(i -> (i - Integer.parseInt(range.get(0))) % value == 0)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toList());
        return new ParsedCronField(fieldTimeType, values);
    }
}
