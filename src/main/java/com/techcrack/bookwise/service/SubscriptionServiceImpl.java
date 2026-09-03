package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AdminRevenueService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.AdminRevenue;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.helper.DiscountHelper;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionServiceImpl extends AbstractRepository<SubscriptionServiceImpl, SubscriptionRepository>
                                    implements SubscriptionService {

    private final UserService userService;
    private final AdminRevenueService adminRevenueService;
    private final DiscountHelper discountHelper;

    public SubscriptionServiceImpl(SubscriptionRepository repo,
                                   UserService userService,
                                   CurrentUserService userSession,
                                   AdminRevenueService adminRevenueService,
                                   DiscountHelper discountHelper) {
        super(SubscriptionServiceImpl.class, repo, userSession);
        this.userService = userService;
        this.adminRevenueService = adminRevenueService;
        this.discountHelper = discountHelper;
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
        repo.deleteById(key);
    }

    @Override
    public Subscription update(Subscription entity) {
        return repo.save(entity);
    }

    @Override
    public Subscription get(long key) {
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(Subscription.class, "Subscription doesn't exists with id : " + key));
    }

    /**
     * It will Activate premium for user one month without considering existing plan
     * @param userId User wants to activate premium plan
     * @return Activated subscription details
     */
    @Transactional
    public Subscription subscriptionPremiumPlanForOneMonth(long userId, DiscountDetails discountDetails) {
        logger.info("Activation Premium Subscription for {} has been started", userId);

        Users user = userService.get(userId);

        Subscription subscription = activateSubscription(user, Subscriptions.PREMIUM, discountDetails);

        logger.info("User Found for Premium subscription {}", user);

        return subscription;
    }

    public Subscription subscriptionFreePlan(Users user) {
        return activateSubscription(user, Subscriptions.FREE, new DiscountDetails(0));
    }

    public Subscription subscriptionBasicPlanForOneMonth(long userId, DiscountDetails discountDetails) {
        logger.info("Activation Free Subscription for {} has been started", userId);

        Users user = userService.get(userId);

        Subscription subscription = activateSubscription(user, Subscriptions.BASE, discountDetails);

        logger.info("User Found for Free subscription {}", user);

        return subscription;
    }

    public Subscription activateSubscription(long userId, Subscriptions subscriptions, DiscountDetails discountDetails) {
        Users user = userService.get(userId);
        return activateSubscription(user, subscriptions, discountDetails);
    }

    /**
     * IMPORTANT Overloaded Method it assumes plan start date is system date
     * @param user User where we want to activate subscription
     * @param subscriptions Plan Type
     * @return Activate subscription details
     */
    public Subscription activateSubscription(Users user, Subscriptions subscriptions, DiscountDetails discountDetails) {
        return activateSubscription(user, subscriptions, ApplicationData.getSystemDate(), discountDetails);
    }

    /**
     * This method responsible for activating one month free or premium or lifetime subscription
     * IMPORTANT It will Automatically inactivate exists plan
     * @param user User where we create a one-month subscription
     * @param subscriptions Subscription Plan
     * @param startDate Plan Start Date
     * @return Returns subscription details related current user
     */
    @Transactional
    public Subscription activateSubscription(Users user, Subscriptions subscriptions, LocalDateTime startDate, DiscountDetails discountDetails) {
        logger.info("Activating One Month free subscription process started.");

        Subscription subscription = new Subscription();
        subscription.initialize(user.getId());

        subscription.setSubscriptions(subscriptions);
        setValidationPeriodBasedOnType(subscription, startDate);
        setTotalAmountBasedOnDiscount(subscription, discountDetails);
        subscription.setUser(user);

        logger.info("Activating One Month free Subscription process completed");

        int rowsAffected = repo.deactivateActiveSubscription(user.getId(), userSession.getCurrentUserId(), ApplicationData.getSystemDate());

        logger.debug("Trying Subscription Deactivation {} rows affected", rowsAffected);

        subscription = register(subscription);

        boolean isRevenueGenerated = adminRevenueService.createRevenueFromSubscription(subscription);

        if (isRevenueGenerated) {
            logger.info("Revenue Generate for admin on subscription");
        } else {
            logger.warn("Failed to Generate admin revenue on subscription");
        }

        return subscription;
    }

    private void setTotalAmountBasedOnDiscount(Subscription subscription, DiscountDetails discountDetails) {
        double discountAmount = discountHelper.discountAmountOnSubscription(subscription, discountDetails);
        double totalRent = subscription.getSubscriptions().getRent();
        subscription.setSubscriptionAmount(totalRent - discountAmount);
    }

    public void setValidationPeriodBasedOnType(Subscription subscription, LocalDateTime dateTime) {
        subscription.setStartDate(dateTime);
        subscription.setEndDate(isLifeTimeSubscription(subscription.getSubscriptions()) ? null : dateTime.plusDays(subscription.getSubscriptions().getDays()));
        subscription.setBooksAllowedPerMonth(subscription.getSubscriptions().getBooksAllowed());
    }

    private boolean isLifeTimeSubscription(Subscriptions subscriptions) {
        return subscriptions.getDays() == Integer.MAX_VALUE;
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

    @Override
    public Subscriptions[] getAllSubscriptions() {
        return Subscriptions.values();
    }
}
