package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;

import java.time.LocalDateTime;

public interface SubscriptionService extends BasicCRUD<Subscription> {
    Subscription subscriptionPremiumForOneMonth(long userId);
    Subscription subscriptionFreeForOneMonth(long userId);
    Subscription activateSubscription(Users user, Subscriptions subscriptions, LocalDateTime startDate);
    Subscription activateSubscription(Users user, Subscriptions subscriptions);
    boolean hasLimitToBorrowBook(long userId);
    int getFreeLimitDays(long userId);
    int updateBookAllowed(long userId, long quantity);
    Subscriptions getSubscription(long userId);
}
