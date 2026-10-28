package com.techcrack.bookwise.applicationServices.subscription.dto.response;

import java.time.LocalDateTime;

public record CurrentSubscriptionDetailResponse(
        long id,
        long planId,
        String name,
        double price,
        String borrowLimit,
        String durationDays,
        String borrowLimitDaysAllowed,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
