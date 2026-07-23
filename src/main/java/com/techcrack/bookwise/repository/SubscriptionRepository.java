package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.RawQueries;
import com.techcrack.bookwise.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Modifying
    @Transactional
    @Query(value = RawQueries.DEACTIVATE_ALL_SUBSCRIPTIONS, nativeQuery = true)
    int deactivateActiveSubscription(@Param("userId") long userId,
                                     @Param("updatedBy") long updatedBy,
                                     @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = RawQueries.BORROW_LIMIT_AVAILABLE, nativeQuery = true)
    boolean existsLimitForBookBorrow(@Param("userId") long userId);
}
