package com.techcrack.bookwise.dtos.subscription.response;

public record SubscriptionUpgradePlanDetail(
        long subscriptionId,
        String planType,
        String borrowLimit,
        String durationAllowed,
        double price
) {
}
