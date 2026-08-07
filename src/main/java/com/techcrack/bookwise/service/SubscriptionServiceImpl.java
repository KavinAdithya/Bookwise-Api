package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionServiceImpl extends AbstractRepository<SubscriptionServiceImpl, SubscriptionRepository>
                                    implements SubscriptionService {

    private final UserService userService;

    public SubscriptionServiceImpl(SubscriptionRepository repo, UserService userService, CurrentUserService userSession) {
        super(SubscriptionServiceImpl.class, repo, userSession);
        this.userService = userService;
    }

    @Transactional
    public Subscription register(Subscription subscription) {
        logger.info("{} User subscription Register process started {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        subscription =  repo.save(subscription);
        logger.debug("Subscription Saved Info : {}", subscription);
        logger.info("{} User subscription process done {}", subscription.getUser().getUsername(), subscription.getSubscriptions());
        return subscription;
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public Subscription update(Subscription entity) {
        return null;
    }

    @Override
    public Subscription get(long key) {
        return null;
    }

    @Transactional
    public Subscription subscriptionPremiumForOneMonth(long userId, LocalDateTime startDate) {
        logger.info("Activation Premium Subscription for {} has been started", userId);

        Subscription subscription = new Subscription();
        subscription.initialize(ApplicationData.HARD_CODED_CURRENT_ID);

        subscription.setSubscriptions(Subscriptions.PREMIUM);
        setValidationPeriodBasedOnType(subscription, startDate);

        Users user = userService.get(userId);
        subscription.setUser(user);
        subscription.setBooksAllowedPerYear(subscription.getSubscriptions().getBooksAllowed());
        logger.info("User Found for subscription {}", user);

        int rowsAffected = repo.deactivateActiveSubscription(userId, ApplicationData.HARD_CODED_CURRENT_ID, ApplicationData.SYSTEM_DATE);

        logger.debug("Subscription Deactivation {} rows affected", rowsAffected);

        return register(subscription);
    }

    public void setValidationPeriodBasedOnType(Subscription subscription, LocalDateTime dateTime) {
        subscription.setStartDate(dateTime);
        subscription.setEndDate(dateTime.plusDays(subscription.getSubscriptions().getDays()));
    }

    @Transactional
    public boolean hasLimitToBorrowBook(long userId) {
        return repo.existsLimitForBookBorrow(userId);
    }

    @Override
    @Transactional
    public int getFreeLimitDays(long userId) {
        return repo.getSubscription(userId).getDays();
    }

    @Override
    @Transactional
    public int updateBookAllowed(long userId, long quantity) {
        return repo.updateBooksAllowed(userId, quantity);
    }

    public Subscriptions getSubscription(long userId) {
        return repo.getSubscription(userId);
    }
}
