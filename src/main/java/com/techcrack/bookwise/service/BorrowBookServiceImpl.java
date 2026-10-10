package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.*;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.BorrowStatus;
import com.techcrack.bookwise.dtos.book.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.dtos.borrowbook.layer.DueAmountDetails;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BookBasicInfo;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookDetailView;
import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookViewContext;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookViewResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBorrowBookDetails;
import com.techcrack.bookwise.dtos.subscription.response.BorrowBookSubscriptionDetail;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.exceptions.customized.*;
import com.techcrack.bookwise.exceptions.customized.checked.DueAmountFailedException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.helper.BorrowBookHelper;
import com.techcrack.bookwise.helper.TimeHelper;
import com.techcrack.bookwise.repository.BorrowBookRepository;
import com.techcrack.bookwise.utils.AbstractService;
import com.techcrack.bookwise.validations.BorrowBookValidations;
import jakarta.transaction.InvalidTransactionException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowBookServiceImpl extends AbstractService<BorrowBookServiceImpl, BorrowBookRepository, BorrowBookValidations>
                                    implements BorrowBookService {
    private final UserService userService;
    private final BookService bookService;
    private final SubscriptionService subscriptionService;
    private final AuthorRevenueService authorRevenueService;
    private final AdminRevenueService adminRevenueService;
    private final BorrowBookHelper helper;

    public BorrowBookServiceImpl(BorrowBookRepository repo,
                                 BorrowBookValidations validations,
                                 UserService userService,
                                 BookService bookService,
                                 SubscriptionService subscriptionService,
                                 CurrentUserService userSession,
                                 BorrowBookHelper helper,
                                 AuthorRevenueService authorRevenueService,
                                 AdminRevenueService adminRevenueService,
                                 TimeHelper timeHelper) {
       super(BorrowBookServiceImpl.class, repo, validations, userSession);
       this.userService = userService;
       this.bookService = bookService;
       this.subscriptionService = subscriptionService;
       this.authorRevenueService = authorRevenueService;
       this.adminRevenueService = adminRevenueService;
       this.helper = helper;
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
     * @param request the borrow request containing the user and book details
     * @return Returns stored borrow entity
     */
    @Transactional
    public BorrowBook borrowBook(BorrowBookRequest request) {
        logger.info("Initiated Process for borrowing book");

        BorrowBook entity = request.buildBorrowBook();

        populateRelations(entity, request);

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
        boolean updated = bookService.updateBookQuantity(request.getBookId(), -request.getQuantity());
        if (!updated) {
            logger.warn("Failed to update book quantity after borrow book");
            throw new TransactionFailedException("Failed for update book quantity");
        }

        return borrowBook;
    }

    @Override
    public BorrowBook getBorrowBookById(long borrowBookId) {
        logger.info("Getting Borrow details");

        BorrowBook borrowBook = repo.findByIdAndIsActiveTrueAndUser_Id(
                borrowBookId,
                userSession.getCurrentUserId()
        ).orElseThrow(
                () -> new ObjectNotFoundException(
                        BorrowBook.class, "Borrow Details doesn't match with " + borrowBookId
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

        LocalDateTime dueDate = helper.computeBorrowDueDate(
                subscriptionService.getFreeLimitDays(userSession.getCurrentUserId())
        );

        borrowBook.initialize(userSession.getCurrentUserId());
        borrowBook.setBorrowDate(ApplicationData.getSystemDate());
        borrowBook.setDueDate(dueDate);

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
    public void populateRelations(BorrowBook borrowBook, BorrowBookRequest request) {
        borrowBook.setBook(
                bookService.get(
                        request.getBookId()
                )
        );

        borrowBook.setUser(
                userService.get(
                       userSession.getCurrentUserId()
                )
        );

        logger.info("Borrow book related entities populated");
    }

    /**
     * Register the Borrow entity with @Transaction Annotation
     * @param entity Saves Borrow Entity
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
     * @param borrowBookId refers to borrow-book where we will compute due amount
     * @return returns calculated due amount
     */
    public DueAmountDetails calculateDueAmount(long borrowBookId) {
        BorrowBook entity = this.getBorrowBookById(borrowBookId);

        return calculateDueAmount(entity);
    }

    @Override
    public ReturnBorrowBookDetails computeReturnDetails(long borrowBookId) {
        logger.info("Return Details computing started");

        BorrowBook borrowBook = getBorrowBookById(borrowBookId);

        if (borrowBook.getStatus() != BorrowStatus.BORROWED)
            throw new InvalidOperationException("Cannot fetch return details for book which is not in borrowed status");

        DueAmountDetails dueAmountDetails = calculateDueAmount(borrowBook);

        logger.info("Return book details computed");

        BookBasicInfo book = new BookBasicInfo(
            borrowBook.getBook().getId(),
            borrowBook.getBook().getTitle(),
            borrowBook.getBook().getDescription(),
            borrowBook.getBook().getCategory().getName(),
            borrowBook.getBook().getAuthor().getUser().getName(),
            borrowBook.getBook().getCoverImageUrl()
        );

        return new ReturnBorrowBookDetails(
                borrowBook.getId(),
                book,
                borrowBook.getQuantity(),
                borrowBook.getBorrowDate(),
                borrowBook.getDueDate(),
                dueAmountDetails
        );
    }

    @Override
    public DueAmountDetails calculateDueAmount(BorrowBook entity) {
        try {
            return helper.calculateDueAmountFromBorrowBook(subscriptionService, userSession.getCurrentUserId(), entity);

        } catch (DueAmountFailedException e) {
            logger.warn("Due Amount Calculation due to {}", e.getMessage());
        }

        return null;
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public BorrowBook update(BorrowBook entity) {
        return null;
    }

    @Override
    public List<BorrowBookViewResponse> getAllBorrowBooksBasedOnCurrentUser() {
        List<BorrowBookViewContext> borrowBookContext = repo.findAllBorrowsBasedOnUser(userSession.getCurrentUserId());
        List<BorrowBookViewResponse> borrowBookViewResponses = new ArrayList<>();

        double dailyFineAmount = subscriptionService.getSubscriptionPlanByUserId(userSession.getCurrentUserId())
                .getDelayDailyFineAmount();

        for (BorrowBookViewContext borrowBook : borrowBookContext) {
            DueAmountDetails dueAmount = null;

            if (borrowBook.borrowStatus() == BorrowStatus.BORROWED) {
                dueAmount = helper.calculateDueAmountDetailsBasedOnDateGap(
                        dailyFineAmount,
                        borrowBook.borrowedQuantity(),
                        borrowBook.borrowedDate(),
                        borrowBook.dueDate(),
                        ApplicationData.getSystemDate()
                );
            }

            double fineAmount = dueAmount == null ? 0 : dueAmount.totalDueAmount();

            borrowBookViewResponses.add(
                    new BorrowBookViewResponse(
                        borrowBook.borrowBookId(),
                        borrowBook.bookId(),
                        borrowBook.coverImageUrl(),
                        borrowBook.bookTitle(),
                        borrowBook.authorName(),
                        borrowBook.borrowedQuantity(),
                        borrowBook.borrowedDate(),
                        borrowBook.dueDate(),
                        borrowBook.returnedAt(),
                        borrowBook.borrowStatus(),
                        BigDecimal.valueOf(fineAmount)
                    )
            );
        }

        return borrowBookViewResponses;
    }

    @Transactional
    @Override
    public void returnBook(ReturnBookContext context) {
        logger.info("Return Book process has been started for Borrow Book Id : {}", context);

        BorrowBook borrowBook = this.getBorrowBookById(context.borrowBookId());

        // Calculation of due amount
        double dueAmount = calculateDueAmount(borrowBook).totalDueAmount();

        if (dueAmount != context.amountPaying()) {
            logger.warn("Amount Paying {} Amount Due {}", context.amountPaying(), dueAmount);
            throw new InvalidDataException("Amount Paying is not equals to the due amount");
        }

        borrowBook.initializeUpdate(userSession.getCurrentUserId());
        borrowBook.setActive(false);
        borrowBook.setStatus(BorrowStatus.RETURNED);
        borrowBook.setReturnDate(ApplicationData.getSystemDate());
        borrowBook.setTotalAmountPaidOnReturn(dueAmount);

        boolean updated = bookService.updateBookQuantity(borrowBook.getBook().getId(), borrowBook.getQuantity());
        if (!updated) {
            logger.warn("Failed to update book quantity after return book");
            throw new TransactionFailedException("Failed for update book quantity");
        }

        boolean isAuthorRevenueGenerated = authorRevenueService.createRevenueFromBorrowBook(borrowBook);

        if (!isAuthorRevenueGenerated) {
            logger.warn("Failed to generate Author Revenue for borrow details {}" , borrowBook);
        }

        if (isAuthorRevenueGenerated) {
            logger.info("Author Revenue Generated Successfully");
        }

        boolean isAdminRevenueGenerated = adminRevenueService.createRevenueFromBorrowBook(borrowBook);

        logger.info(isAdminRevenueGenerated ? "Admin Revenue Generated Successfully" : "Admin Revenue Not Generated It might be no due amount on return amount");
    }

    @Override
    public BorrowBookDetailView getBorrowBookDetailView(long borrowBookId) {
        return repo.getBorrowBookDetailViewByBorrowBookId(borrowBookId);
    }

    @Override
    public BorrowBookConfirmationDetail computeBorrowBookConfirmationDetails(BorrowBookRequest request) {
        logger.info("Request Received to get borrow book confirmation details");

        if (!bookService.checkBookAvailability(request.getBookId(), request.getQuantity())) {
            logger.warn("Book Quantity not available");
            throw new OutOfStockException("Book is out of stock");
        }

        boolean hasLimit = subscriptionService.hasLimitToBorrowBook(userSession.getCurrentUserId());
        if (!hasLimit) {
            logger.warn("User doesn't have limit to borrow a book");
            throw new UpgradeSubscriptionException("User doesn't have limit to borrow a book");
        }

        Book book = bookService.getBookById(request.getBookId());
        Subscription subscription = subscriptionService.getSubscriptionByUserId(userSession.getCurrentUserId());

        BorrowBookSubscriptionDetail subscriptionDetail = new BorrowBookSubscriptionDetail(
                subscription.getId(),
                subscription.getSubscriptions(),
                subscription.getEndDate(),
                subscription.getSubscriptions().getBooksAllowed(),
                subscription.getBooksAllowedPerMonth(),
                subscription.getSubscriptions().getDelayDailyFineAmount()
        );

        LocalDateTime dueDate = helper.computeBorrowDueDate(
                subscriptionService.getFreeLimitDays(userSession.getCurrentUserId())
        );

        return new BorrowBookConfirmationDetail(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAvailableCopies(),
                book.getAuthor().getUser().getName(),
                book.getCategory().getName(),
                book.getCoverImageUrl(),
                dueDate,
                subscriptionDetail
        );
    }

    @Override
    public BorrowBook get(long key) {
        return repo.findByIdAndIsActiveTrue(key)
                .orElseThrow(() -> new ObjectNotFoundException(BorrowBook.class, "Borrow Book is Not available"));
    }
}