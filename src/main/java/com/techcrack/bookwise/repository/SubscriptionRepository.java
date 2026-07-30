package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.queries.JPQLQueries;
import com.techcrack.bookwise.constans.queries.RawQueries;
import com.techcrack.bookwise.constans.Subscriptions;
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

    @Query(value = JPQLQueries.HAS_LIMIT_EXISTS_FOR_BORROW_BOOK)
    boolean existsLimitForBookBorrow(@Param("userId") long userId);

    @Query(JPQLQueries.FETCH_SUBSCRIPTIONS)
    Subscriptions getSubscription(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = RawQueries.UPDATE_SUBSCRIPTION_BOOK_ALLOWED_COUNT, nativeQuery = true)
    int updateBooksAllowed(@Param("userId") long userId,
                           @Param("quantity") long quantity);
}
