package com.techcrack.bookwise.dtos.subscription.response;

import java.time.LocalDateTime;

public record CurrentSubscriptionDetailResponse(
        long id,
        long planId,
        String name,
        double price,
        String borrowLimit,
        String durationDays,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
