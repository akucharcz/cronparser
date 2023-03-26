package org.example;

import org.example.parser.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var cronMapper = new CronMapper();
        var fieldParserController = new FieldParserController(List.of(new CommandParser(),
                new CommaParser(),
                new DigitParser(),
                new RangeParser(),
                new StepParser(),
                new WildCardParser()));
        var responseHandler = new ResponseHandler();
        var cronService = new CronService(cronMapper, fieldParserController, responseHandler);
        cronService.expandCron(args);
    }
}