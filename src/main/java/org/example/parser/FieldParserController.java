package org.example.parser;

import org.example.model.CronField;
import org.example.model.ParsedCronField;

import java.util.List;

public class FieldParserController {
    private final List<FieldParser> fieldParsers;

    public FieldParserController(List<FieldParser> fieldParsers) {
        this.fieldParsers = fieldParsers;
    }

    public ParsedCronField parseField(CronField cronField) {
        return fieldParsers.stream()
                .filter(parser -> parser.applies(cronField.fieldParsingType()))
                .map(parser -> parser.parse(cronField.value(), cronField.fieldTimeType()))
                .findFirst()
                .get();
    }
}
