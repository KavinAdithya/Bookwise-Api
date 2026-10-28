package com.techcrack.bookwise.applicationServices.subscription.dto.request;

import com.techcrack.bookwise.constans.enums.Subscriptions;

public record SubscriptionUpgradeRequest(
        Subscriptions currentPlan,
        Subscriptions newPlan,
        double amountPaid
) {
}
