package com.techcrack.bookwise.applicationServices.subscription.dto.request;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.applicationServices.subscription.entity.Subscription;

public class SubscriptionRegisterResponse {
    private Subscriptions plan;

    public SubscriptionRegisterResponse() {
        super();
    }

    public Subscriptions getPlan() {
        return plan;
    }

    public void setPlan(Subscriptions plan) {
        this.plan = plan;
    }

    public Subscription buildSubscription() {
        Subscription subscription = new Subscription();
        subscription.initialize(null);
        subscription.setSubscriptions(plan);

        return subscription;
    }
    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "subscriptions=" + plan +
                '}';
    }
}
