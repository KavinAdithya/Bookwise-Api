package com.techcrack.bookwise.dtos;

import com.techcrack.bookwise.constans.Subscriptions;

public class SubscriptionDTO {
    private Subscriptions subscriptions;

    public SubscriptionDTO() {
        super();
    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }


    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "subscriptions=" + subscriptions +
                '}';
    }
}
