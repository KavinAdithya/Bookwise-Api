package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionRegisterDTO;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionResponse;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionMapper {
    public Subscription mapToSubscription(SubscriptionRegisterDTO source) {

        return source.buildSubscription();
    }

    public SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
            subscription.getEndDate(),
            subscription.getStartDate(),
            subscription.getSubscriptions()
        );
    }

    public SubscriptionPlanResponse mapToSubscriptionPlanResponse(long id, Subscriptions subscriptions) {
        return new SubscriptionPlanResponse(id, subscriptions.name().toUpperCase(), subscriptions.getRent());
    }

    public List<SubscriptionPlanResponse> mapToSubscriptionPlanResponses(Subscriptions[] subscriptions) {
        List<SubscriptionPlanResponse> subscriptionPlanResponseList = new ArrayList<>();

        long idGenerate = 0;

        for (Subscriptions subscription : subscriptions) {
            subscriptionPlanResponseList.add(mapToSubscriptionPlanResponse(idGenerate++, subscription));
        }

        return subscriptionPlanResponseList;
    }
}
