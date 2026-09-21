package com.techcrack.bookwise.dtos.subscription.response;

import com.techcrack.bookwise.constans.enums.Subscriptions;

import java.time.LocalDateTime;

public record SubscriptionDetails(long id, Subscriptions plan, LocalDateTime startDate, LocalDateTime endDate) {
}
