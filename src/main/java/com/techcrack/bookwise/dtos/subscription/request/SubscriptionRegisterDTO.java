package com.techcrack.bookwise.dtos.subscription.request;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;

public class SubscriptionRegisterDTO {
    private Subscriptions plan;

    public SubscriptionRegisterDTO() {
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
