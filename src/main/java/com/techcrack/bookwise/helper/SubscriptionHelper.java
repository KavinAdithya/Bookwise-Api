package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanDetailResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionHelper {
    public List<SubscriptionPlanDetailResponse> getAllAvailablePlans() {
        List<SubscriptionPlanDetailResponse> availablePlans = new ArrayList<>();

        for (Subscriptions subscriptions : Subscriptions.values()) {
            availablePlans.add(new SubscriptionPlanDetailResponse(
                    subscriptions.ordinal(),
                    subscriptions.toString(),
                    subscriptions.getRent(),
                    convertIntoStringBasedOnUnlimited(subscriptions.getBooksAllowed()),
                    convertIntoStringBasedOnUnlimited(subscriptions.getDays())
            ));
        }

        return availablePlans;
    }

    public String convertIntoStringBasedOnUnlimited(long num) {
        return num == Integer.MAX_VALUE ? "Unlimited" : "" + num;
    }
}
