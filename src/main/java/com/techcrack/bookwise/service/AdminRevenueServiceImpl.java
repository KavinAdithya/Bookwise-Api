package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AdminRevenueService;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.entity.AdminRevenue;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.repository.AdminRevenueRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminRevenueServiceImpl extends AbstractRepository<AdminRevenueServiceImpl, AdminRevenueRepository>
        implements AdminRevenueService {

    public AdminRevenueServiceImpl( AdminRevenueRepository repo, CurrentUserService userSession) {
        super(AdminRevenueServiceImpl.class, repo, userSession);
    }

    @Override
    public boolean createRevenueFromSubscription(Subscription subscription) {
        logger.info("Revenue Creation for Admin on subscription process started");

        if (subscription == null) {
            return false;
        }

        AdminRevenue adminRevenue = createAdminRevenue();

        adminRevenue.setAmount(subscription.getSubscriptionAmount());
        adminRevenue.setSourceId(subscription.getId());
        adminRevenue.setSourceType(IncomeType.USER_SUBSCRIPTION);

        register(adminRevenue);

        logger.info("Revenue Creation for Admin on subscription process completed");
        return true;
    }

    @Override
    public boolean createRevenueFromBorrowBook(BorrowBook borrowBook) {
        logger.info("Revenue Creation for Admin on borrow book process started");

        if (borrowBook == null || borrowBook.getTotalAmountPaidOnReturn() <= 0) {
            return false;
        }

        AdminRevenue adminRevenue = createAdminRevenue();

        adminRevenue.setAmount(borrowBook.getTotalAmountPaidOnReturn());
        adminRevenue.setSourceId(borrowBook.getId());
        adminRevenue.setSourceType(IncomeType.BOOK_BORROW_FEE);

        register(adminRevenue);

        logger.info("Revenue Creation for Admin on borrow book process completed");
        return true;
    }

    @Override
    public boolean createRevenueFromPurchaseBook(PurchaseBook purchaseBook) {
        if (purchaseBook == null || purchaseBook.getBook() == null)
            return false;

        double bookSoldCost = purchaseBook.getTotalAmount();
        double commission = purchaseBook.getBook().getCommissionPercentage();

        double commissionAmount = bookSoldCost * commission / 100;

        AdminRevenue adminRevenue = createAdminRevenue();

        adminRevenue.setSourceType(IncomeType.BOOK_PURCHASE_FEE);
        adminRevenue.setSourceId(purchaseBook.getId());
        adminRevenue.setAmount(commissionAmount);

        register(adminRevenue);


        return true;
    }

    private AdminRevenue createAdminRevenue() {
        AdminRevenue adminRevenue = new AdminRevenue();

        adminRevenue.initialize(userSession.getCurrentUserId());
        adminRevenue.setIncomeDate(ApplicationData.getSystemDate());

        return adminRevenue;
    }


    @Override
    public AdminRevenue register(AdminRevenue entity) {
        return repo.save(entity);
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public AdminRevenue update(AdminRevenue entity) {
        return null;
    }

    @Override
    public AdminRevenue get(long key) {
        return null;
    }
}
