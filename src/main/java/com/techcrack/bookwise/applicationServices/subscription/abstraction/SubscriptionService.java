package com.techcrack.bookwise.applicationServices.subscription.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.applicationServices.subscription.dto.DiscountDetails;
import com.techcrack.bookwise.applicationServices.subscription.dto.request.SubscriptionUpgradeRequest;
import com.techcrack.bookwise.applicationServices.subscription.dto.response.CurrentSubscriptionWithAvailablePlanResponse;
import com.techcrack.bookwise.applicationServices.subscription.dto.response.SubscriptionUpgradeDetailResponse;
import com.techcrack.bookwise.applicationServices.subscription.entity.Subscription;
import com.techcrack.bookwise.applicationServices.user.entity.Users;

import java.time.LocalDateTime;

public interface SubscriptionService extends BasicCRUD<Subscription> {
    Subscription subscriptionPremiumPlanForOneMonth(long userId, DiscountDetails discountDetails);
    Subscription subscriptionBasicPlanForOneMonth(long userId, DiscountDetails discountDetails);
    Subscription activateSubscription(Users user, Subscriptions subscriptions, LocalDateTime startDate, DiscountDetails discountDetails);
    Subscription activateSubscription(Users user, Subscriptions subscriptions, DiscountDetails discountDetails);
    Subscription subscriptionFreePlan(Users user);
    boolean hasLimitToBorrowBook(long userId);
    int getFreeLimitDays(long userId);
    int updateBookAllowed(long userId, long quantity);
    Subscriptions getSubscriptionPlanByUserId(long userId);
    Subscriptions[] getAllSubscriptions();
    Subscription getSubscriptionByUserId(long userId);
    CurrentSubscriptionWithAvailablePlanResponse findCurrentUserPlanWithAvailablePlans();
    void inactivateExpiredSubscriptionAndActivateFreePlan(LocalDateTime currentDateTime);
    void upgradeCurrentSubscriptionPlan(SubscriptionUpgradeRequest newPlanDetails);
    SubscriptionUpgradeDetailResponse getCurrentAndUpgradePlanDetails(int subscriptionPlanId);
}

