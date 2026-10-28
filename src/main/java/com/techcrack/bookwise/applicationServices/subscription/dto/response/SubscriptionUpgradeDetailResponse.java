package com.techcrack.bookwise.applicationServices.subscription.dto.response;

public record SubscriptionUpgradeDetailResponse(
    SubscriptionUpgradePlanDetail currentPlan,
    SubscriptionUpgradePlanDetail newPlan
    ){
}
