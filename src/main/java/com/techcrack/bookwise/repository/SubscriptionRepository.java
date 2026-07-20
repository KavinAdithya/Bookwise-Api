package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.JPQLQueries;
import com.techcrack.bookwise.constans.RawQueries;
import com.techcrack.bookwise.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Modifying
    @Transactional
    @Query(value = RawQueries.DEACTIVATE_SUBSCRIPTION_ALL, nativeQuery = true)
    int deactivateActiveSubscription(@Param("userId") long userId,
                                     @Param("updatedBy") long updatedBy,
                                     @Param("updatedAt") LocalDateTime updatedAt);
}
