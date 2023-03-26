package org.example;

import org.example.exceptions.ArgumentSizeException;
import org.example.exceptions.FieldTypeNotMatchedException;
import org.example.exceptions.FieldsNumberException;
import org.example.model.CronField;
import org.example.model.FieldParsingType;
import org.example.model.FieldTimeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class CronMapper {
    private static final int EXPECTED_ARGUMENTS_NUMBER = 6;
    private static final String WILDCARD_GROUP = "(^\\*$)";
    private static final String STEP_GROUP = "(^\\*/\\d{1,2}$)";
    private static final String RANGE_GROUP = "(^\\d-\\d{1,2}$)";
    private static final String COMMA_GROUP = "(^(?:\\d{1,2},\\d{1,2})+$)";
    private static final String DIGIT_GROUP = "(^\\d{1,2}$)";
    private static final Map<FieldParsingType, Pattern> CRON_GROUPS = Map.of(
            FieldParsingType.WILDCARD, Pattern.compile(WILDCARD_GROUP),
            FieldParsingType.STEP, Pattern.compile(STEP_GROUP),
            FieldParsingType.RANGE, Pattern.compile(RANGE_GROUP),
            FieldParsingType.COMMA, Pattern.compile(COMMA_GROUP),
            FieldParsingType.DIGIT, Pattern.compile(DIGIT_GROUP)
    );

    public List<CronField> mapArgumentToCronFields(String[] cliArgument) {
        var tokens = splitArgumentIntoTokens(cliArgument);
        List<CronField> cronFieldList = new ArrayList<>();
        for (int i = 0; i < EXPECTED_ARGUMENTS_NUMBER; i++) {
            cronFieldList.add(mapTokenIntoCronField(tokens[i], i));
        }
        return cronFieldList;
    }

    private String[] splitArgumentIntoTokens(String[] cliArgument) {
        if (cliArgument.length > 1) {
            throw new ArgumentSizeException();
        }
        return Optional.ofNullable(cliArgument[0])
                .map(argument -> argument.split(" ", EXPECTED_ARGUMENTS_NUMBER))
                .filter(tokens -> tokens.length == EXPECTED_ARGUMENTS_NUMBER)
                .orElseThrow(FieldsNumberException::new);
    }

    private CronField mapTokenIntoCronField(String field, int index) throws FieldTypeNotMatchedException {
        CronField cronField = null;
        if (index == EXPECTED_ARGUMENTS_NUMBER - 1) {
            return new CronField(FieldParsingType.COMMAND, field, FieldTimeType.COMMAND);
        }
        for (Map.Entry<FieldParsingType, Pattern> entry : CRON_GROUPS.entrySet()) {
            if (entry.getValue().matcher(field).matches()) {
                cronField = new CronField(entry.getKey(), field, FieldTimeType.indexToFieldType(index));
                break;
            }
        }
        return Optional.ofNullable(cronField)
                .orElseThrow(FieldTypeNotMatchedException::new);
    }

}
