package com.techcrack.bookwise.dtos;

import com.techcrack.bookwise.constans.Subscriptions;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class SubscriptionResponseDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Subscriptions subscriptions;

    public SubscriptionResponseDTO(LocalDateTime endDate, LocalDateTime startDate, Subscriptions subscriptions) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.subscriptions = subscriptions;
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

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "SubscriptionResponseDTO{" +
                "endDate=" + endDate +
                ", startDate=" + startDate +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
