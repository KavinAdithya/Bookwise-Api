package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class DiscountHelper {
    public double discountAmountOnSubscription(Subscription subscription, DiscountDetails discountDetails) {
        if (subscription == null || subscription.getSubscriptions() == null || discountDetails == null)
            return 0.0;

        double rentAmount = subscription.getSubscriptions().getRent();
        double discountApplied = discountDetails.discountPercentage() / 100.00;

        return calculateDiscountedAmount(rentAmount, discountApplied);
    }

    private double calculateDiscountedAmount(double amount, double percentage) {
        return amount * percentage;
    }
}
