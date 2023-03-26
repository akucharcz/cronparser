package org.example;

import org.example.exceptions.ArgumentSizeException;
import org.example.exceptions.FieldTypeNotMatchedException;
import org.example.exceptions.FieldsNumberException;
import org.example.model.CronField;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CronMapperTest {

    private static final CronMapper cronMapper = new CronMapper();

    @Test
    public void shouldProperlyMapArgumentIntoCronFields() {
        //given
        var argument = new String[]{"*/15 0 1,15 * 1-5 > /usr/bin/find"};
        var expectedCronFields = List.of(
                new CronField(FieldParsingType.STEP, "*/15", FieldTimeType.MINUTE),
                new CronField(FieldParsingType.DIGIT, "0", FieldTimeType.HOUR),
                new CronField(FieldParsingType.COMMA, "1,15", FieldTimeType.DAY_OF_THE_MONTH),
                new CronField(FieldParsingType.WILDCARD, "*", FieldTimeType.MONTH),
                new CronField(FieldParsingType.RANGE, "1-5", FieldTimeType.DAY_OF_THE_WEEK),
                new CronField(FieldParsingType.COMMAND, "> /usr/bin/find", FieldTimeType.COMMAND)
        );
        //when
        var result = cronMapper.mapArgumentToCronFields(argument);
        //then
        assertEquals(result, expectedCronFields);
    }

    @Test
    public void shouldThrowFieldsNumberExceptionWithWrongSizeArgument() {
        //given
        var argument = new String[]{"*/15 * 1-5 > /usr/bin/find"};
        //when
        //then
        assertThrows(FieldsNumberException.class, () -> cronMapper.mapArgumentToCronFields(argument));
    }

    @Test
    public void shouldThrowFieldTypeNotMatchedExceptionWhenTokenCannotBeParsed() {
        //given
        var argument = new String[]{"*/15 something 1,15 * 1-5 > /usr/bin/find"};
        //when
        //then
        assertThrows(FieldTypeNotMatchedException.class, () -> cronMapper.mapArgumentToCronFields(argument));
    }

    @Test
    public void shouldThrowArgumentSizeExceptionExceptionWhenNumberOfArgumentsIsGreaterThanOne() {
        //given
        var argument = new String[]{"*/15 0 1,15 * 1-5 > /usr/bin/find", ""};
        //when
        //then
        assertThrows(ArgumentSizeException.class, () -> cronMapper.mapArgumentToCronFields(argument));
    }
}