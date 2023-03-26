package org.example.parser;

import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;

sealed interface FieldParser permits CommaParser, CommandParser, DigitParser, RangeParser, StepParser, WildCardParser {

    boolean applies(FieldParsingType fieldParsingType);

    ParsedCronField parse(String field, FieldTimeType fieldTimeType);
}
