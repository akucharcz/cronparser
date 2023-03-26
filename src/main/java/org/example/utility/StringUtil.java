package org.example.utility;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<String> mapListOfIntegersToStrings(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
