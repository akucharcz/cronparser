package org.example.parser;

import org.example.exceptions.IncorrectArgumentValuesException;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.example.utility.RangeVerifier;
import org.example.utility.StringUtil;

import java.util.List;

public final class DigitParser implements FieldParser {
    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.DIGIT == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        var value = List.of(Integer.valueOf(field));
        if (!RangeVerifier.isInRange(fieldTimeType, value)) {
            throw new IncorrectArgumentValuesException(fieldTimeType.toString());
        }
        return new ParsedCronField(fieldTimeType, StringUtil.mapListOfIntegersToStrings(value));
    }
}
