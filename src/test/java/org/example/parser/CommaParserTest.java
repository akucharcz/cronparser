package org.example.parser;

import org.example.model.FieldParsingType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommaParserTest {
    private final CommaParser commaParser = new CommaParser();

    @Test
    public void shouldApplyForRangeType() {
        //given
        var fieldParsingType = FieldParsingType.COMMA;
        //when
        var result = commaParser.applies(fieldParsingType);
        //then
        assertTrue(result);
    }

    @ParameterizedTest
    @EnumSource(value = FieldParsingType.class, names = "COMMA", mode = EnumSource.Mode.EXCLUDE)
    public void shouldNotApplyForOtherTypes(FieldParsingType fieldParsingType) {
        //given
        //when
        var result = commaParser.applies(fieldParsingType);
        //then
        assertFalse(result);
    }

}