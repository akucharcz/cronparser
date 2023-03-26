package org.example.model;

public enum FieldTimeType {
    MINUTE, HOUR, DAY_OF_THE_MONTH, MONTH, DAY_OF_THE_WEEK, COMMAND;

    private static final String SWITCH_MAPPING_ERROR = "Improper index value";

    public static FieldTimeType indexToFieldType(int index) {
        return switch (index) {
            case 0 -> FieldTimeType.MINUTE;
            case 1 -> FieldTimeType.HOUR;
            case 2 -> FieldTimeType.DAY_OF_THE_MONTH;
            case 3 -> FieldTimeType.MONTH;
            case 4 -> FieldTimeType.DAY_OF_THE_WEEK;
            case 5 -> FieldTimeType.COMMAND;
            default -> throw new RuntimeException(SWITCH_MAPPING_ERROR);
        };
    }
}
