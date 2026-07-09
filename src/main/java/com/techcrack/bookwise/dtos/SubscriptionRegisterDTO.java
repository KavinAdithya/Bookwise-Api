package com.techcrack.bookwise.dtos;

import com.techcrack.bookwise.constans.Subscriptions;

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


    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "subscriptions=" + plan +
                '}';
    }
}
