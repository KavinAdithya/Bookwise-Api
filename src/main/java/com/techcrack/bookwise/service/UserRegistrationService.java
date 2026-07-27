package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.BaseLoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService extends BaseLoggerService<UserRegistrationService, UserService> {

    private final SubscriptionService subscriptionService;

    public UserRegistrationService(UserService service, SubscriptionService subscriptionService) {
        super(UserRegistrationService.class, service);
        this.subscriptionService = subscriptionService;
    }

    public RegistrationResult<Users, Subscription> register(Users user, Subscription subscription) {
        logger.info("User Registration for {} started ", user.getUsername());

        user = service.register(user);

        logger.info("User Registration done  for {} ", user.getUsername());

        subscription.setUser(user);
        subscription = subscriptionService.register(subscription);

        logger.info("Subscription Registration done. Completed Registration Process");
        return new RegistrationResult<>(user, subscription);
    }
}
