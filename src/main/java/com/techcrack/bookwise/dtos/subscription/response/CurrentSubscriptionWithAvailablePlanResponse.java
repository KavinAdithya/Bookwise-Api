package com.techcrack.bookwise.dtos.subscription.response;

import java.util.List;

public record CurrentSubscriptionWithAvailablePlanResponse(
        CurrentSubscriptionDetailResponse currentPlan,
        List<SubscriptionPlanDetailResponse> availablePlans
) {
}
