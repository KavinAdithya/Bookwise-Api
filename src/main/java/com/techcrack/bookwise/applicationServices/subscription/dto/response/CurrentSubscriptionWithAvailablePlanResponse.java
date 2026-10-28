package com.techcrack.bookwise.applicationServices.subscription.dto.response;

import java.util.List;

public record CurrentSubscriptionWithAvailablePlanResponse(
        CurrentSubscriptionDetailResponse currentPlan,
        List<SubscriptionPlanDetailResponse> availablePlans
) {
}
