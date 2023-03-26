package org.example.exceptions;

public class FieldTypeNotMatchedException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Wrong format of fields";

    public FieldTypeNotMatchedException() {
        super(EXCEPTION_MESSAGE);
    }
}
