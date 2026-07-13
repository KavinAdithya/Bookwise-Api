package com.techcrack.bookwise.service;

import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repo;
    private final Logger logger;

    public SubscriptionService(SubscriptionRepository repo) {
        this.repo = repo;
        this.logger = LoggerFactory.getLogger(SubscriptionService.class);
    }

    public Subscription register(Subscription subscription) {
        logger.info("{} User subscription Register process started {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        subscription =  repo.save(subscription);
        logger.debug("Subscription Saved Info : {}", subscription);
        logger.info("{} User subscription process done {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        return subscription;
    }
}
