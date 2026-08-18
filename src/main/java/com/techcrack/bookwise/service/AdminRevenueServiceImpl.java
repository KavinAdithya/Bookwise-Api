package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AdminRevenueService;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.entity.AdminRevenue;
import com.techcrack.bookwise.entity.BorrowBook;
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

        AdminRevenue adminRevenue = new AdminRevenue();

        adminRevenue.initialize(userSession.getCurrentUserId());
        adminRevenue.setAmount(subscription.getSubscriptionAmount());
        adminRevenue.setSourceId(subscription.getId());
        adminRevenue.setSourceType(IncomeType.USER_SUBSCRIPTION);
        adminRevenue.setIncomeDate(ApplicationData.SYSTEM_DATE);

        register(adminRevenue);

        logger.info("Revenue Creation for Admin on subscription process completed");
        return true;
    }

    @Override
    public boolean createRevenueFromBorrowBook(BorrowBook borrowBook) {
        logger.info("Revenue Creation for Admin on borrow book process started");

        if (borrowBook == null) {
            return false;
        }

        AdminRevenue adminRevenue = new AdminRevenue();

        adminRevenue.initialize(userSession.getCurrentUserId());
        adminRevenue.setAmount(borrowBook.getTotalAmountPaidOnReturn());
        adminRevenue.setSourceId(borrowBook.getId());
        adminRevenue.setSourceType(IncomeType.BOOK_BORROW_FEE);
        adminRevenue.setIncomeDate(ApplicationData.SYSTEM_DATE);

        register(adminRevenue);

        logger.info("Revenue Creation for Admin on borrow book process completed");
        return true;
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
