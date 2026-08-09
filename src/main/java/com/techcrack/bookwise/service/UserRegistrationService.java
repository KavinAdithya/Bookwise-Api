package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService extends AbstractLogger<UserRegistrationService> {

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final CurrentUserService userSession;

    public UserRegistrationService(UserService userService, SubscriptionService subscriptionService, CurrentUserService userSession) {
        super(UserRegistrationService.class);
        this.userService = userService;
        this.userSession = userSession;
        this.subscriptionService = subscriptionService;
    }

    public RegistrationResult<Users, Subscription> register(Users user, Subscription subscription) {
        logger.info("User Registration for {} started ", user.getUsername());

        user = userService.register(user);

        logger.info("User Registration done  for {} ", user.getUsername());

        subscription.setUser(user);
        subscription = subscriptionService.register(subscription);

        logger.info("Subscription Registration done. Completed Registration Process");
        return new RegistrationResult<>(user, subscription);
    }
}
