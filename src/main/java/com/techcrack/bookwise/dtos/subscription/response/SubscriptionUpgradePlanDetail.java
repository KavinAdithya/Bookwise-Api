package com.techcrack.bookwise.dtos.subscription.response;

public record SubscriptionUpgradePlanDetail(
        long subscriptionId,
        String planType,
        String borrowLimit,
        String durationAllowed,
        String borrowLimitDaysAllowed,
        double price
) {
}
