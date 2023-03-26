package org.example;

import org.example.exceptions.ArgumentSizeException;
import org.example.exceptions.FieldTypeNotMatchedException;
import org.example.exceptions.FieldsNumberException;
import org.example.exceptions.IncorrectArgumentValuesException;
import org.example.model.ParsedCronField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseHandler {
    private static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error occurred";

    public void createResponse(List<ParsedCronField> parsedCronField) {
        String response;
        try {
            response = parsedCronField.stream()
                    .map(ParsedCronField::toString)
                    .collect(Collectors.joining("\n"));
        } catch (ArgumentSizeException | FieldsNumberException | FieldTypeNotMatchedException |
                 IncorrectArgumentValuesException e) {
            response = e.getMessage();
        } catch (Exception e) {
            response = UNEXPECTED_ERROR_MESSAGE + Arrays.toString(e.getStackTrace());
        }
        System.out.println(response);
    }
}
