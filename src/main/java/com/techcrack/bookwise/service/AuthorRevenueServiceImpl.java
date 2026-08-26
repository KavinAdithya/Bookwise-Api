package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorRevenueService;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.entity.AuthorRevenue;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.helper.AuthorRevenueHelper;
import com.techcrack.bookwise.repository.AuthorRevenueRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthorRevenueServiceImpl extends AbstractRepository<AuthorRevenueServiceImpl, AuthorRevenueRepository>
        implements AuthorRevenueService {

    private final AuthorRevenueHelper helper;

    public AuthorRevenueServiceImpl(AuthorRevenueRepository repo, CurrentUserService userSession, AuthorRevenueHelper helper) {
        super(AuthorRevenueServiceImpl.class, repo, userSession);
        this.helper = helper;
    }

    @Override
    public AuthorRevenue register(AuthorRevenue entity) {
        return repo.save(entity);
    }

    @Override
    public void remove(long key) {
        repo.deleteById(key);
    }

    @Override
    public AuthorRevenue update(AuthorRevenue entity) {
        return repo.save(entity);
    }

    @Override
    public AuthorRevenue get(long key) {
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(AuthorRevenue.class, "Author Revenue not found with id " + key));
    }

    @Override
    @Transactional
    public boolean createFromBorrowBook(BorrowBook borrowBook) {
        logger.info("Recording revenue for author process started");

        if (borrowBook == null || borrowBook.getBook() == null || borrowBook.getBook().getAuthor() == null)
            return false;

        double revenueAmount = helper.calculateBorrowBookRevenue(borrowBook);

        if (revenueAmount <= 0)
            return false;

        long authorId = borrowBook.getBook().getAuthor().getId();

        AuthorRevenue revenue = new AuthorRevenue();

        revenue.initialize(userSession.getCurrentUserId());

        revenue.setAuthorId(authorId);
        revenue.setAmount(revenueAmount);
        revenue.setSourceType(IncomeType.BOOK_BORROW_FEE);
        revenue.setIncomeDate(ApplicationData.getSystemDate());
        revenue.setAmountDisbursed(false);
        revenue.setSourceId(borrowBook.getId());

        register(revenue);

        logger.info("Revenue Recorded Details {}", revenue);

        return true;
    }
}
