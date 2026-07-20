package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repo;
    private final Logger logger;
    private final UserService userService;

    public SubscriptionService(SubscriptionRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(SubscriptionService.class);
    }

    @Transactional
    public Subscription register(Subscription subscription) {
        logger.info("{} User subscription Register process started {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        subscription =  repo.save(subscription);
        logger.debug("Subscription Saved Info : {}", subscription);
        logger.info("{} User subscription process done {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        return subscription;
    }

    @Transactional
    public Subscription subscriptionPremiumForOneMonth(long userId, LocalDateTime startDate) {
        logger.info("Activation Premium Subscription for {} has been started", userId);

        Subscription subscription = new Subscription();
        subscription.initialize();

        subscription.setSubscriptions(Subscriptions.PREMIUM);
        setValidationPeriodBasedOnType(subscription, startDate);

        Users user = userService.get(userId);
        subscription.setUser(user);
        logger.info("User Found for subscription {}", user);

        int rowsAffected = repo.deactivateActiveSubscription(userId, ApplicationData.HARD_CODED_CURRENT_ID, ApplicationData.SYSTEM_DATE);

        logger.debug("Subscription Deactivation {} rows affected", rowsAffected);

        return register(subscription);
    }

    public void setValidationPeriodBasedOnType(Subscription subscription, LocalDateTime dateTime) {
        subscription.setStartDate(dateTime);
        subscription.setEndDate(dateTime.plusDays(subscription.getSubscriptions().getDays()));
    }
}
