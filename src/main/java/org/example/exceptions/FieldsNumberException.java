package org.example.exceptions;

public class FieldsNumberException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Incorrect fields number: cron argument should have 6 fields: minute " +
            "hour, day of the month, month, day of the week, command to execute";

    public FieldsNumberException() {
        super(EXCEPTION_MESSAGE);
    }
}
