package com.TicketSelling.TicketSelling.Enum;

import org.springframework.data.domain.Sort;

public enum SortOrder {
    ASC, DEFAULT,DESC;

    public Sort.Direction toSpringSortOrder() {
        return this == ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
