package com.techcrack.bookwise.dtos.subscription.response;

public record SubscriptionUpgradeDetailResponse(
    SubscriptionUpgradePlanDetail currentPlan,
    SubscriptionUpgradePlanDetail newPlan
    ){
}
