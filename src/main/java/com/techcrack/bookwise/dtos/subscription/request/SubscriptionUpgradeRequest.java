package com.techcrack.bookwise.dtos.subscription.request;

import com.techcrack.bookwise.constans.enums.Subscriptions;

public record SubscriptionUpgradeRequest(
        Subscriptions currentPlan,
        Subscriptions newPlan,
        double amountPaid
) {
}
