package com.techcrack.bookwise.dtos.subscription.response;

public record SubscriptionUpgradePlanDetail(
        long subscriptionId,
        String planType,
        int borrowLimit,
        int durationAllowed
) {
}
