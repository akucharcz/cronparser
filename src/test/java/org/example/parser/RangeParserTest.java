package org.example.parser;

import org.example.exceptions.IncorrectArgumentValuesException;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.example.model.ParsedCronField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RangeParserTest {

    private final RangeParser rangeParser = new RangeParser();

    @Test
    public void shouldApplyForRangeType() {
        //given
        var fieldParsingType = FieldParsingType.RANGE;
        //when
        var result = rangeParser.applies(fieldParsingType);
        //then
        assertTrue(result);
    }

    @ParameterizedTest
    @EnumSource(value = FieldParsingType.class, names = "RANGE", mode = EnumSource.Mode.EXCLUDE)
    public void shouldNotApplyForOtherTypes(FieldParsingType fieldParsingType) {
        //given
        //when
        var result = rangeParser.applies(fieldParsingType);
        //then
        assertFalse(result);
    }

    @Test
    public void shouldParseRangeCorrectly() {
        //given
        var range = "1-13";
        var expectedOutput = new ParsedCronField(FieldTimeType.MINUTE, List.of("1", "2", "3", "4", "5", "6", "7", "8"
                , "9", "10", "11",
                "12", "13"));
        //when
        var result = rangeParser.parse(range, FieldTimeType.MINUTE);
        //then
        assertEquals(expectedOutput, result);
    }

    @Test
    public void shoulThrowIncorrectArgumentValuesExceptionIfValuesAreOutOfRange() {
        //given
        var range = "1-13";
        //when
        //then
        assertThrows(IncorrectArgumentValuesException.class, () -> rangeParser.parse(range, FieldTimeType.MONTH));

    }

    //  private static Object[][]

}