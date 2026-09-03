package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionService extends BasicCRUD<Subscription> {
    Subscription subscriptionPremiumPlanForOneMonth(long userId, DiscountDetails discountDetails);
    Subscription subscriptionBasicPlanForOneMonth(long userId, DiscountDetails discountDetails);
    Subscription activateSubscription(Users user, Subscriptions subscriptions, LocalDateTime startDate, DiscountDetails discountDetails);
    Subscription activateSubscription(Users user, Subscriptions subscriptions, DiscountDetails discountDetails);
    Subscription subscriptionFreePlan(Users user);
    boolean hasLimitToBorrowBook(long userId);
    int getFreeLimitDays(long userId);
    int updateBookAllowed(long userId, long quantity);
    Subscriptions getSubscription(long userId);
    Subscriptions[] getAllSubscriptions();
}
