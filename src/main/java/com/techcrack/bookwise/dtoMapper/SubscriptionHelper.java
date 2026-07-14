package com.techcrack.bookwise.dtoMapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.SubscriptionRegisterDTO;
import com.techcrack.bookwise.dtos.SubscriptionResponseDTO;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionHelper {
    public Subscription mapToSubscription(SubscriptionRegisterDTO source) {
        Subscription subscription = new Subscription();

        subscription.setSubscriptions(source.getPlan());
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
