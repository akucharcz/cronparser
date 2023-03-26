package org.example.parser;

import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;

import java.util.List;

public final class CommandParser implements FieldParser {
    @Override
    public boolean applies(FieldParsingType fieldParsingType) {
        return FieldParsingType.COMMAND == fieldParsingType;
    }

    @Override
    public ParsedCronField parse(String field, FieldTimeType fieldTimeType) {
        return new ParsedCronField(fieldTimeType, List.of(field));
    }
}
