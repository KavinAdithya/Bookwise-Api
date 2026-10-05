package com.techcrack.bookwise.scheduler;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.ApplicationData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionScheduler {
    private final SubscriptionService subscriptionService;

    public SubscriptionScheduler(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Scheduled(fixedRate = 6000 * 60)
    public void inactivateExpiredSubscriptions() {
        subscriptionService.inactivateExpiredSubscriptionAndActivateFreePlan(ApplicationData.getSystemDate());
    }
}