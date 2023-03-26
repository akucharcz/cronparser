package org.example.exceptions;

public class ArgumentSizeException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Incorrect argument size: cron command should be provided as one argument";

    public ArgumentSizeException() {
        super(EXCEPTION_MESSAGE);
    }
}
