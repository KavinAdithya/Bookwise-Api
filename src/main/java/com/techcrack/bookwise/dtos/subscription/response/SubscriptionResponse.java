package com.techcrack.bookwise.dtos.subscription.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "plan",
        "startDate",
        "endDate"
})
public class SubscriptionResponse {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Subscriptions plan;

    public SubscriptionResponse(LocalDateTime endDate, LocalDateTime startDate, Subscriptions plan) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.plan = plan;
    }

    public SubscriptionResponse(Subscription subscriptions) {
        this.plan = subscriptions.getSubscriptions();
        this.startDate = subscriptions.getStartDate();
        this.endDate = subscriptions.getEndDate();
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Subscriptions getPlan() {
        return plan;
    }

    public void setPlan(Subscriptions plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "SubscriptionResponseDTO{" +
                "endDate=" + endDate +
                ", startDate=" + startDate +
                ", subscriptions=" + plan +
                '}';
    }
}
