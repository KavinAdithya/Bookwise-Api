package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Subscription;

import java.time.LocalDateTime;

public interface SubscriptionService extends BasicCRUD<Subscription> {
    Subscription subscriptionPremiumForOneMonth(long userId, LocalDateTime startDate);
    boolean hasLimitToBorrowBook(long userId);
}
