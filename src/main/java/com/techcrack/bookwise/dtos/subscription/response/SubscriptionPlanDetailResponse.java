package com.techcrack.bookwise.dtos.subscription.response;

public record SubscriptionPlanDetailResponse(
        long id,
        String name,
        double price,
        String borrowLimit,
        String durationDays
) {
}
