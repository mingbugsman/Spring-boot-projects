package com.TicketSelling.TicketSelling.Utils;

import java.util.List;
import java.util.stream.Collectors;

public final class SafeCastList {
    public static <T> List<T> safeCastList(Object obj, Class<T> type) {
        if (!(obj instanceof List<?> rawList)) {
            throw new IllegalArgumentException("Expected a List but got " + (obj == null ? "null" : obj.getClass().getSimpleName()));
        }

        return rawList.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
}
