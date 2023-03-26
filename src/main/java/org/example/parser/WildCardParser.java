package org.example.parser;

import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.example.utility.RangeExpander;

public final class WildCardParser implements FieldParser {
    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.WILDCARD == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        return new ParsedCronField(fieldTimeType, RangeExpander.expandRange(fieldTimeType));
    }
}
