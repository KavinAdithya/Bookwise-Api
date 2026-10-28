package com.techcrack.bookwise.applicationServices.subscription.dto.response;

import com.techcrack.bookwise.constans.enums.Subscriptions;

import java.time.LocalDateTime;

public record SubscriptionDetails(long id, Subscriptions plan, LocalDateTime startDate, LocalDateTime endDate) {
}
