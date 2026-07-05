package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.dtos.SubscriptionDTO;
import com.techcrack.bookwise.dtos.SubscriptionResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionHelper {
    public Subscription mapToSubscription(SubscriptionDTO source) {
        Subscription subscription = new Subscription();

        subscription.setSubscriptions(source.getSubscriptions());
        subscription.setStartDate(ApplicationData.SYSTEM_DATE);
        subscription.setEndDate(
                ApplicationData.SYSTEM_DATE.plusDays(subscription.getSubscriptions().getDays())
        );
        subscription.initialize();

        return subscription;
    }

    public SubscriptionResponseDTO mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponseDTO(
            subscription.getEndDate(),
            subscription.getStartDate(),
            subscription.getSubscriptions()
        );
    }
}
