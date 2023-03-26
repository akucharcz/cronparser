package org.example.utility;

import org.example.model.FieldTimeType;
import org.example.model.Range;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.utility.Ranges.*;

public class RangeExpander {

    public static List<String> expandRange(Range range) {
        return IntStream.rangeClosed(range.min(), range.max())
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> expandRange(FieldTimeType fieldTimeType) {
        return switch (fieldTimeType) {
            case MINUTE -> RangeExpander.expandRange(MINUTE_RANGE);
            case HOUR -> RangeExpander.expandRange(HOUR_RANGE);
            case DAY_OF_THE_MONTH -> RangeExpander.expandRange(DAY_OF_TGE_MONTH_RANGE);
            case MONTH -> RangeExpander.expandRange(MONTH_RANGE);
            case DAY_OF_THE_WEEK -> RangeExpander.expandRange(DAY_OF_THE_WEEK_RANGE);
            case COMMAND -> Collections.EMPTY_LIST;
        };
    }
}
