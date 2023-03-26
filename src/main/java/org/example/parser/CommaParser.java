package org.example.parser;

import org.example.exceptions.IncorrectArgumentValuesException;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.example.utility.RangeVerifier;
import org.example.utility.StringUtil;

import java.util.Arrays;

public final class CommaParser implements FieldParser {
    private static final String COMMA = ",";

    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.COMMA == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        var values = Arrays.stream(field.split(COMMA))
                .mapToInt(Integer::valueOf)
                .boxed()
                .toList();
        if (!RangeVerifier.isInRange(fieldTimeType, values)) {
            throw new IncorrectArgumentValuesException(fieldTimeType.toString());
        }
        return new ParsedCronField(fieldTimeType, StringUtil.mapListOfIntegersToStrings(values));

    }
}
