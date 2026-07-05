package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
