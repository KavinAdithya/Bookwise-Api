package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanDetailResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionUpgradePlanDetail;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionHelper {
    private Subscriptions[] subscriptions;
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

    public SubscriptionUpgradePlanDetail convertSubscriptionsToDetail(Subscriptions subscriptions) {
        return new SubscriptionUpgradePlanDetail(
                subscriptions.ordinal(),
                subscriptions.name(),
                convertIntoStringBasedOnUnlimited(subscriptions.getBooksAllowed()),
                convertIntoStringBasedOnUnlimited(subscriptions.getDays()),
                subscriptions.getRent()
        );
    }

    public Subscriptions findSubscriptionById(int subscriptionId) {
        if (subscriptions == null)
            subscriptions = Subscriptions.values();

        if (subscriptionId < 0 || subscriptionId >= subscriptions.length)
            throw new InvalidDataException("Invalid Subscription Plan Id");

        return subscriptions[subscriptionId];
    }
}
