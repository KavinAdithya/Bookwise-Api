package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AdminRevenueService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Subscriptions;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionRegisterResponse;
import com.techcrack.bookwise.dtos.subscription.request.SubscriptionUpgradeRequest;
import com.techcrack.bookwise.dtos.subscription.response.CurrentSubscriptionDetailResponse;
import com.techcrack.bookwise.dtos.subscription.response.CurrentSubscriptionWithAvailablePlanResponse;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionPlanDetailResponse;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.exceptions.customized.TransactionFailedException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.helper.DiscountHelper;
import com.techcrack.bookwise.helper.SubscriptionHelper;
import com.techcrack.bookwise.repository.SubscriptionRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import com.techcrack.bookwise.validations.SubscriptionValidation;
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
    private final SubscriptionHelper helper;
    private final SubscriptionValidation validation;

    public SubscriptionServiceImpl(SubscriptionRepository repo,
                                   UserService userService,
                                   CurrentUserService userSession,
                                   AdminRevenueService adminRevenueService,
                                   DiscountHelper discountHelper,
                                   SubscriptionHelper helper,
                                   SubscriptionValidation validation) {
        super(SubscriptionServiceImpl.class, repo, userSession);
        this.userService = userService;
        this.adminRevenueService = adminRevenueService;
        this.discountHelper = discountHelper;
        this.helper = helper;
        this.validation = validation;
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
        subscription.initialize(userSession.getCurrentUserId());

        subscription.setSubscriptions(subscriptions);
        setValidationPeriodBasedOnType(subscription, startDate);
        setTotalAmountBasedOnDiscount(subscription, discountDetails);
        subscription.setUser(user);

        logger.info("Activating One Month free Subscription process completed");

        int rowsAffected = repo.deactivateActiveSubscription(user.getId(), user.getId(), ApplicationData.getSystemDate());

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
        return repo.getSubscriptionPlanByUserId(userId).getDays();
    }

    @Override
    @Transactional
    public int updateBookAllowed(long userId, long quantity) {
        return repo.updateBooksAllowed(userId, quantity);
    }

    public Subscriptions getSubscriptionPlanByUserId(long userId) {
        return repo.getSubscriptionPlanByUserId(userId);
    }

    @Override
    public Subscriptions[] getAllSubscriptions() {
        return Subscriptions.values();
    }

    @Override
    public Subscription getSubscriptionByUserId(long userId) {
        return repo.getSubscriptionByIsActiveTrueAndUser_Id(userId);
    }

    @Override
    public CurrentSubscriptionWithAvailablePlanResponse findCurrentUserPlanWithAvailablePlans() {
        logger.info("Process started to fetch current user and available plan details");

        List<SubscriptionPlanDetailResponse> availablePlans = helper.getAllAvailablePlans();

        Subscription currentSubscription = getSubscriptionByUserId(userSession.getCurrentUserId());

        CurrentSubscriptionDetailResponse currentSubscriptionDetailResponse = new CurrentSubscriptionDetailResponse(
                currentSubscription.getId(),
                currentSubscription.getSubscriptions().ordinal(),
                currentSubscription.getSubscriptions().toString(),
                currentSubscription.getSubscriptionAmount(),
                helper.convertIntoStringBasedOnUnlimited(currentSubscription.getBooksAllowedPerMonth()),
                helper.convertIntoStringBasedOnUnlimited(currentSubscription.getSubscriptions().getDays()),
                currentSubscription.getStartDate(),
                currentSubscription.getEndDate()
        );

        return new CurrentSubscriptionWithAvailablePlanResponse(
                currentSubscriptionDetailResponse,
                availablePlans
        );
    }

    @Transactional
    public void inactivateExpiredSubscriptionAndActivateFreePlan(LocalDateTime currentDateTime) {
        logger.info("Process Started to in activate expired subscription");

        List<Long> expiredUserIds = repo.findAllExpiredUserIds(currentDateTime);

        logger.debug("USER IDS found for in activate subscription {}", expiredUserIds);

        if (expiredUserIds.isEmpty()) {
            logger.info("No Users found to inactivate subscription");
            return;
        }

        repo.deactivateSubscriptionExpired(expiredUserIds);

        logger.debug("Inactivated subscription which got expired");

        DiscountDetails discountDetails = new DiscountDetails(0);

        for (Long userId : expiredUserIds) {
            activateSubscription(userId, Subscriptions.FREE, new DiscountDetails(0));
        }

        logger.info("Subscription inactivated and activated free plan");
    }

    @Override
    @Transactional
    public void upgradeCurrentSubscriptionPlan(SubscriptionUpgradeRequest request) {
        logger.info("Request Received to upgrade current user plan USER SESSION {}", userSession.getCurrentUserName());

        Errors errors = validation.isValidSubscriptionUpgradeDetails(request);

        if (errors.hasErrors()) {
            logger.warn(errors.getData());
            throw new InvalidDataException(errors.getData());
        }

        logger.debug("Upgraded Request data validated");

        int rowsAffected = repo.deactivateActiveSubscription(userSession.getCurrentUserId(), userSession.getCurrentUserId(), ApplicationData.getSystemDate());

        if (rowsAffected == 0) {
            throw new TransactionFailedException("Failed to inactivate current user");
        }

        Subscription  subscription = activateSubscription(userSession.getCurrentUserId(), request.newPlan(), new DiscountDetails(0));

        logger.info("Subscription activated with details of {}", subscription);
    }
}