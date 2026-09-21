package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanResponse;
import com.techcrack.bookwise.mapper.SubscriptionMapper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController extends AbstractController<SubscriptionController, SubscriptionService, SubscriptionMapper> {

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper mapper, CurrentUserService userSession) {
        super(SubscriptionController.class, subscriptionService, mapper, userSession);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<ApiResponseEntity<List<SubscriptionPlanResponse>>> getAllActiveSubscriptionPlans() {
        logger.info("Request Received to fetch all subscriptions available");

        Subscriptions[] subscriptions = service.getAllSubscriptions();

        List<SubscriptionPlanResponse> responses = mapper.mapToSubscriptionPlanResponses(subscriptions);

        logger.info("Request Completed for fetch all subscriptions available");
        return ResponseEntityHelper
                .buildSuccessResponse("Subscriptions Fetched Successfully", responses);
    }

}
