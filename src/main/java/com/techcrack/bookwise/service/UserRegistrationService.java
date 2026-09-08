package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.enums.Roles;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.AbstractLogger;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService extends AbstractLogger<UserRegistrationService> {

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public UserRegistrationService(UserService userService, SubscriptionService subscriptionService) {
        super(UserRegistrationService.class);
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @Transactional
    public RegistrationResult<Users, Subscription> register(Users user, Subscriptions subscriptions) {
        logger.info("User Registration for {} started ", user.getUsername());

        user.setRole(user.getRole() == null ? Roles.USER : user.getRole());
        user = userService.register(user);

        logger.info("User Registration done  for {} ", user.getUsername());

        Subscription subscription = subscriptionService.activateSubscription(user, subscriptions, new DiscountDetails(0));

        logger.info("Subscription Registration done. Completed Registration Process");
        return new RegistrationResult<>(user, subscription);
    }
}
