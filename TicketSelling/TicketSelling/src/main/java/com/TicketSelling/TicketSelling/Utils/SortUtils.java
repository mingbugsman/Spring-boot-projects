package com.TicketSelling.TicketSelling.Utils;

import com.TicketSelling.TicketSelling.Enum.SortOrder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUtils {


    public static <T, U extends Comparable<? super U>> List<T> sortList(
            List<T> list, SortOrder order, Function<T, U> keyExtractor) {
        
        if (order == SortOrder.DEFAULT) {
            return list;
        }

        return list.stream()
                .sorted(order == SortOrder.ASC
                        ? Comparator.comparing(keyExtractor)
                        : Comparator.comparing(keyExtractor).reversed())
                .collect(Collectors.toList());
    }
}
