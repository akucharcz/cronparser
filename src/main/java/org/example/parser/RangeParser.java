package org.example.parser;

import org.example.exceptions.IncorrectArgumentValuesException;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.example.model.Range;
import org.example.utility.RangeExpander;
import org.example.utility.RangeVerifier;

import java.util.Arrays;
import java.util.Optional;

public final class RangeParser implements FieldParser {
    private static final String RANGE = "-";

    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.RANGE == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        var range = extractRange(field);
        var values = Optional.of(range)
                .filter(r -> RangeVerifier.isInRange(fieldTimeType, r))
                .map(RangeExpander::expandRange)
                .orElseThrow(() -> new IncorrectArgumentValuesException(fieldTimeType.toString()));
        return new ParsedCronField(fieldTimeType, values);
    }

    private Range extractRange(String field) {
        var splitValues = Arrays.stream(field.split(RANGE))
                .mapToInt(Integer::valueOf)
                .boxed()
                .toList();
        return new Range(splitValues.get(0), splitValues.get(1));
    }
}
