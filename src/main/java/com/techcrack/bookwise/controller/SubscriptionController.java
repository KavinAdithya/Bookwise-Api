package com.techcrack.bookwise.controller;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionUpgradeRequest;
import com.techcrack.bookwise.dtos.subscription.response.CurrentSubscriptionWithAvailablePlanResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionUpgradeDetailResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionUpgradePlanDetail;
import com.techcrack.bookwise.mapper.SubscriptionMapper;
import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/subscriptions/me")
    public ResponseEntity<ApiResponseEntity<CurrentSubscriptionWithAvailablePlanResponse>>  getCurrentUserSubscriptionAndAvailablePlans() {
        logger.info("Request received to fetch current user subscription details with available plans");

        CurrentSubscriptionWithAvailablePlanResponse response = service.findCurrentUserPlanWithAvailablePlans();

        logger.info("Request completed to fetch current user subscription and available plans");

        return ResponseEntityHelper
                .buildSuccessResponse("Subscription Details Fetched", response);
    }

    @PostMapping("/subscriptions/upgrade")
    public ResponseEntity<ApiResponseEntity<Object>> upgradeSubscription(@RequestBody SubscriptionUpgradeRequest request) {
        logger.info("Request received to upgraded subscription");

        service.upgradeCurrentSubscriptionPlan(request);

        logger.info("Request completed to upgraded subscription");

        return ResponseEntityHelper
                .buildSuccessResponseWithoutBody("Subscription Upgraded Successfully");
    }

    @GetMapping("/subscriptions/confirm/upgrade/{subscriptionPlanId}")
    public ResponseEntity<ApiResponseEntity<SubscriptionUpgradeDetailResponse>> getSubscriptionDetailsOfCurrentAndUpgradePlan(@PathVariable("subscriptionPlanId") int subscriptionPlanId) {
        logger.info("Request Received to get current and upgrade plan details");

        SubscriptionUpgradeDetailResponse response = service.getCurrentAndUpgradePlanDetails(subscriptionPlanId);

        logger.info("Request Completed to get current and upgrade plan details");

        return ResponseEntityHelper
                .buildSuccessResponse("Fetched Current and Upgrade Plan Details", response);
    }
}
