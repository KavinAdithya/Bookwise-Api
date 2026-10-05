package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionUpgradeRequest;
import com.techcrack.bookwise.exceptions.templates.Errors;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionValidation {
    public Errors isValidSubscriptionUpgradeDetails(SubscriptionUpgradeRequest request) {
        Errors errors = new Errors();

        if (request == null) {
            errors.addErrorMessage("Request Data is not available");
            return errors;
        }

        if (request.newPlan() == null || request.currentPlan() == null) {
            errors.addErrorMessage("New Plan or current plan is missing in the request");
            return errors;
        }

        if (isBothSamePlan(request.currentPlan(), request.newPlan())) {
            errors.addErrorMessage("Cannot upgrade same plan");
        }

        if (request.amountPaid() != request.newPlan().getRent()) {
            errors.addErrorMessage("For upgrade new plan should match amount . Expected Amount is "  + request.newPlan().getRent() +
                    "  Actual amount paid " + request.amountPaid());
        }

        return errors;
    }

    public boolean isBothSamePlan(Subscriptions first, Subscriptions second) {
        return first == second;
    }
}
