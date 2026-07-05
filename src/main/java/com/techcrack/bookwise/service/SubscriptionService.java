package com.techcrack.bookwise.service;

import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repo;

    public SubscriptionService(SubscriptionRepository repo) {
        this.repo = repo;
    }

    public Subscription register(Subscription subscription) {
        return repo.save(subscription);
    }
}
