package org.example.parser;

import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WildCardParserTest {
    private final WildCardParser wildCardParser = new WildCardParser();

    private static Object[][] fieldTimeTypeAndExpectedRange() {
        return new Object[][]{
                {FieldTimeType.MINUTE, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
                        , 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
                        49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59)},
                {FieldTimeType.HOUR, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        20, 21, 22, 23)},
                {FieldTimeType.DAY_OF_THE_MONTH, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                        24, 25, 26, 27, 28, 29, 30, 31)},
                {FieldTimeType.MONTH, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)},
                {FieldTimeType.DAY_OF_THE_WEEK, List.of(0, 1, 2, 3, 4, 5, 6)}
        };
    }

    @Test
    public void shouldApplyForRangeType() {
        //given
        var fieldParsingType = FieldParsingType.WILDCARD;
        //when
        var result = wildCardParser.applies(fieldParsingType);
        //then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @EnumSource(value = FieldParsingType.class, names = "WILDCARD", mode = EnumSource.Mode.EXCLUDE)
    public void shouldNotApplyForOtherTypes(FieldParsingType fieldParsingType) {
        //given
        //when
        var result = wildCardParser.applies(fieldParsingType);
        //then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("fieldTimeTypeAndExpectedRange")
    public void shouldReturnProperRangeForWildCard(Object fieldTimeType, Object values) {
        //given
        //when
        var result = wildCardParser.parse("", (FieldTimeType) fieldTimeType);
        //then
        assertEquals(result, values);

    }
}