package org.example.utility;

import org.example.model.FieldTimeType;
import org.example.model.Range;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.utility.Ranges.*;

public class RangeVerifier {

    public static boolean isInRange(FieldTimeType fieldTimeType, Range range) {
        return switch (fieldTimeType) {
            case MINUTE -> isRangeWithinLimit(range, MINUTE_RANGE);
            case HOUR -> isRangeWithinLimit(range, HOUR_RANGE);
            case DAY_OF_THE_MONTH -> isRangeWithinLimit(range, DAY_OF_TGE_MONTH_RANGE);
            case MONTH -> isRangeWithinLimit(range, MONTH_RANGE);
            case DAY_OF_THE_WEEK -> isRangeWithinLimit(range, DAY_OF_THE_WEEK_RANGE);
            case COMMAND -> true;
        };
    }

    public static boolean isInRange(FieldTimeType fieldTimeType, List<Integer> values) {
        var summary = values.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        var range = new Range(summary.getMin(), summary.getMax());

        return isInRange(fieldTimeType, range);
    }

    private static boolean isRangeWithinLimit(Range compared, Range limit) {
        return compared.min() >= limit.min() && compared.max() <= limit.max();
    }
}
