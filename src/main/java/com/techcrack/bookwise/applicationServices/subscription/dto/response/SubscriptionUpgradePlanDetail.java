package com.techcrack.bookwise.applicationServices.subscription.dto.response;

public record SubscriptionUpgradePlanDetail(
        long subscriptionId,
        String planType,
        String borrowLimit,
        String durationAllowed,
        String borrowLimitDaysAllowed,
        double price
) {
}
