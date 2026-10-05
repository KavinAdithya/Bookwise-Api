package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import static com.techcrack.bookwise.constans.queries.jpql.Subscription.*;
import static com.techcrack.bookwise.constans.queries.sql.Subscription.*;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Modifying
    @Transactional
    @Query(value = DEACTIVATE_ALL_SUBSCRIPTIONS, nativeQuery = true)
    int deactivateActiveSubscription(@Param("userId") long userId,
                                     @Param("updatedBy") long updatedBy,
                                     @Param("updatedAt") LocalDateTime updatedAt);

    @Query(HAS_LIMIT_EXISTS_FOR_BORROW_BOOK)
    boolean existsLimitForBookBorrow(@Param("userId") long userId);

    @Query(FETCH_SUBSCRIPTIONS)
    Subscriptions getSubscriptionPlanByUserId(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = UPDATE_SUBSCRIPTION_BOOK_ALLOWED_COUNT, nativeQuery = true)
    int updateBooksAllowed(@Param("userId") long userId,
                           @Param("quantity") long quantity);

    Subscription getSubscriptionByIsActiveTrueAndUser_Id(long userId);

    @Modifying
    @Query(value = INACTIVATE_EXPIRED_SUBSCRIPTIONS, nativeQuery = true)
    void deactivateSubscriptionExpired(List<Long> userIds);

    @Query(FETCH_EXPIRED_USERID)
    List<Long> findAllExpiredUserIds(LocalDateTime currentSystemDate);
}
