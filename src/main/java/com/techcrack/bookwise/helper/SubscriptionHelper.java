package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.dtos.SubscriptionRegisterDTO;
import com.techcrack.bookwise.dtos.SubscriptionResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionHelper {
    public Subscription mapToSubscription(SubscriptionRegisterDTO source) {
        Subscription subscription = new Subscription();
        subscription.initialize(ApplicationData.HARD_CODED_CURRENT_ID);

        subscription.setSubscriptions(Subscriptions.FREE);
        subscription.setStartDate(ApplicationData.SYSTEM_DATE);
        subscription.setEndDate(
                ApplicationData.SYSTEM_DATE.plusDays(subscription.getSubscriptions().getDays())
        );
        subscription.setBooksAllowedPerMonth(subscription.getSubscriptions().getBooksAllowed());

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
