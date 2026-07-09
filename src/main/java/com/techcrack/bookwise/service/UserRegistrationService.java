package com.techcrack.bookwise.service;

import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.utils.responseHelper.RegistrationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final UserService service;
    private final SubscriptionService subscriptionService;
    private final Logger logger;

    public UserRegistrationService(UserService service, SubscriptionService subscriptionService ) {
        this.service = service;
        this.subscriptionService = subscriptionService;
        this.logger = LoggerFactory.getLogger(UserRegistrationService.class);
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
