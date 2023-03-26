package org.example.exceptions;

public class IncorrectArgumentValuesException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Value of provided argument for field %s is incorrect";

    public IncorrectArgumentValuesException(String field) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, field));
    }
}
