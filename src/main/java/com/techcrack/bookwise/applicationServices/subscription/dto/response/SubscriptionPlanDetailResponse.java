package com.techcrack.bookwise.applicationServices.subscription.dto.response;

public record SubscriptionPlanDetailResponse(
        long id,
        String name,
        double price,
        String borrowLimit,
        String borrowLimitDays,
        String durationDays
) {
}
