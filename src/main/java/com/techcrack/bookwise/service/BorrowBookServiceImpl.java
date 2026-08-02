package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.BorrowStatus;
import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.BorrowBookRepository;
import com.techcrack.bookwise.utils.AbstractService;
import com.techcrack.bookwise.validations.BorrowBookValidations;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowBookServiceImpl extends AbstractService<BorrowBookServiceImpl, BorrowBookRepository, BorrowBookValidations>
                                    implements BorrowBookService {
    private final UserService userService;
    private final BookService bookService;
    private final SubscriptionService subscriptionService;

    public BorrowBookServiceImpl(BorrowBookRepository repo, BorrowBookValidations validations, UserService userService, BookService bookService, SubscriptionService subscriptionService) {
       super(BorrowBookServiceImpl.class, repo, validations);
       this.userService = userService;
       this.bookService = bookService;
       this.subscriptionService = subscriptionService;
    }

    /**
     * Validate, sets borrow details and persist the data
     *  <p>
     *      Does following operations
     *  </p>
     *  <ul>
     *      <li>Populated related entities</li>
     *      <li>Validate all details</li>
     *      <li>update borrow details like borrow date etc.</li>
     *      <li>Stores and returns the stored entity</li>
     *  </ul>
     * @param entity the borrow request containing the user and book details
     * @return Returns stored borrow entity
     */
    public BorrowBook borrowBook(BorrowBook entity) {
        logger.info("Initiated Process for borrowing book");

        populateRelations(entity);

        Errors errors = validations.isValidBorrow(entity);

        if (errors.hasErrors()) {
            String message = "Failed to Borrow Book : " + errors.getData();
            logger.warn(message);
            throw new InvalidDataException(message);
        }

        setBorrowDetails(entity);

        logger.info("Borrow Book details validated and added successfully");
        BorrowBook borrowBook = register(entity);

        logger.info("Updating Book limit for the user");
        int rowsAffected = subscriptionService.updateBookAllowed(borrowBook.getUser().getId(), borrowBook.getQuantity());

        logger.debug("On updating books allowed {} rows data changed", rowsAffected);
        return borrowBook;
    }

    @Override
    public BorrowBook getBorrowDetails(BorrowBookContext context) {
        logger.info("Getting Borrow details");

        BorrowBook borrowBook = repo.findByBorrowDateAndBook_IdAndUser_Id(
                context.borrowDate(),
                context.bookId(),
                context.userId()
        ).orElseThrow(
                () -> new ObjectNotFoundException(
                        BorrowBook.class, "Borrow Details doesn't match with " + context
                )
        );

        logger.info("Finished to fetch borrow details");
        return borrowBook;
    }

    /**
     * Sets borrow details
     *  <p>
     *      Sets following details :
     *  </p>
     *  <ul>
     *      <li>Sets Borrow Date</li>
     *      <li>Sets Due date to return</li>
     *      <li>Update status as borrowed</li>
     *  </ul>
     * @param borrowBook the borrow request containing the user and book details
     */
    public void setBorrowDetails(BorrowBook borrowBook) {
        logger.info("Setting borrow details");

        borrowBook.setBorrowDate(ApplicationData.SYSTEM_DATE);
        borrowBook.setDueDate(ApplicationData.SYSTEM_DATE.plusDays(
                subscriptionService.getFreeLimitDays(
                        borrowBook.getUser().getId()
                )
        ));

        borrowBook.setStatus(BorrowStatus.BORROWED);

    }

    /**
     * Populate Borrow Book Related entities
     * <p>
     * Populates Following Entities
     * <ul>
     *     <li>Based on book id populates Book entity</li>
     *     <li>Based on user id populated User entity</li>
     * </ul>
     * @param borrowBook the borrow request containing the user and book details
     */
    public void populateRelations(BorrowBook borrowBook) {
        borrowBook.setBook(
                bookService.get(
                        borrowBook.getBook().getId()
                )
        );

        borrowBook.setUser(
                userService.get(
                        borrowBook.getUser().getId()
                )
        );

        logger.info("Borrow book related entities populated");
    }

    /**
     * Register the Borrow entity with @Transaction Annotation
     * @param entity
     * @return returns stored entity
     */
    @Override
    @Transactional
    public BorrowBook register(BorrowBook entity) {
        return repo.save(entity);
    }

    /**
     * Calculate the due amount for a returning book.
     * Based on System Date
     * @param context
     * @return
     */
    public double calculateDueAmount(BorrowBookContext context) {
        BorrowBook entity = getBorrowDetails(context);

        if (ApplicationData.SYSTEM_DATE.isBefore(entity.getDueDate())) {
            return 0;
        }

        long daysDelayed = ChronoUnit.DAYS.between(entity.getDueDate(), ApplicationData.SYSTEM_DATE);

        double dailyRent = subscriptionService.getSubscription(entity.getUser().getId())
                .getDelayDailyFineAmount();

        return dailyRent * daysDelayed;
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public BorrowBook update(BorrowBook entity) {
        return null;
    }

    public List<BorrowBook> getBorrowDetails(long userId) {
        return repo.findByUser_Id(userId);
    }

    @Override
    public BorrowBook get(long key) {
        return null;
    }

}
