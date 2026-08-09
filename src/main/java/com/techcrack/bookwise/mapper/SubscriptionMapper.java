package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionRegisterDTO;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionResponse;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    public Subscription mapToSubscription(SubscriptionRegisterDTO source) {

//        subscription.setStartDate(ApplicationData.SYSTEM_DATE);
//        subscription.setEndDate(
//                ApplicationData.SYSTEM_DATE.plusDays(subscription.getSubscriptions().getDays())
//        );
//        subscription.setBooksAllowedPerMonth(subscription.getSubscriptions().getBooksAllowed());

        return source.buildSubscription();
    }

    public SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
            subscription.getEndDate(),
            subscription.getStartDate(),
            subscription.getSubscriptions()
        );
    }
}
