package org.example;

import org.example.parser.FieldParserController;

public class CronService {
    private final CronMapper cronMapper;
    private final FieldParserController fieldParserController;
    private final ResponseHandler responseHandler;

    public CronService(CronMapper cronMapper, FieldParserController fieldParserController, ResponseHandler responseHandler) {
        this.cronMapper = cronMapper;
        this.fieldParserController = fieldParserController;
        this.responseHandler = responseHandler;
    }

    public void expandCron(String[] cronArgument) {
        var cronFields = cronMapper.mapArgumentToCronFields(cronArgument);
        var parsedCronField = cronFields.stream()
                .map(fieldParserController::parseField)
                .toList();
         responseHandler.createResponse(parsedCronField);
    }

}
