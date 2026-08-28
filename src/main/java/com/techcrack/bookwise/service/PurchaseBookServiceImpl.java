package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.*;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookAmountCalculateRequest;
import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookRequest;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.RevenueGenerationFailedException;
import com.techcrack.bookwise.helper.PurchaseBookHelper;
import com.techcrack.bookwise.repository.PurchaseBookRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseBookServiceImpl extends AbstractRepository<PurchaseBookServiceImpl, PurchaseBookRepository>
                                    implements PurchaseBookService {
    private final BookService bookService;
    private final UserService userService;
    private final AdminRevenueService adminRevenueService;
    private final AuthorRevenueService authorRevenueService;
    private final PurchaseBookHelper helper;

    public PurchaseBookServiceImpl(
            PurchaseBookRepository repo,
            BookService bookService,
            UserService userService,
            CurrentUserService userSession,
            AdminRevenueService adminRevenueService,
            AuthorRevenueService authorRevenueService,
            PurchaseBookHelper helper) {
        super(PurchaseBookServiceImpl.class, repo, userSession);
        this.bookService = bookService;
        this.userService = userService;
        this.adminRevenueService = adminRevenueService;
        this.authorRevenueService = authorRevenueService;
        this.helper = helper;
    }

    @Override
    public PurchaseBook register(PurchaseBook entity) {
        logger.info("Book Purchase Process Started for User {} Book {}", entity.getUser().getId(), entity.getBook().getId());

        entity = repo.save(entity);

        logger.info("Book has been successfully purchased.");

        return entity;
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public PurchaseBook update(PurchaseBook entity) {
        return null;
    }

    @Override
    public PurchaseBook get(long key) {
        return null;
    }

    @Override
    @Transactional
    public PurchaseBook purchaseBook(PurchaseBookRequest request) {
        if (request.quantity() <= 0)  {
            logger.warn("For Purchasing Book Invalid Quantity {}", request.quantity());
            throw new InvalidDataException("Invalid Purchase Book Quantity " + request.quantity());
        }
        logger.info("Purchasing Book Process Started");

        boolean available = bookService.checkAvailability(request.bookId(), request.quantity());

        if (!available) {
            logger.error("Requested book stock is not available");
            throw new InvalidDataException("Book Quantity not available as you requested Quantity : "+ request.quantity());
        }

        logger.info("Book Quantity Available");

        boolean res = bookService.updateBookAvailability(request.bookId(), request.quantity());

        if (!res) {
            throw new InvalidDataException("Failed to update book count");
        }

        PurchaseBook entity = new PurchaseBook();

        entity.initialize(userSession.getCurrentUserId());

        populateRelationships(entity, request);
        entity.setPurchaseDate(ApplicationData.getSystemDate());
        entity.setQuantity(request.quantity());
        double totalPurchaseAmount = helper.calculateTotalAmountFromPurchaseBook(entity);

        if (totalPurchaseAmount != request.purchaseAmount()) {
            String failureMessage = buildInvalidRequestAmount(totalPurchaseAmount, request.purchaseAmount()).toString();
            logger.warn("Amount Mismatch between Actual and request amount to purchase a book");
            throw new InvalidDataException(failureMessage);
        }

        entity.setTotalAmount(totalPurchaseAmount);

        logger.info("Purchasing Related Data has been computed and ready to purchase {}", entity);
        PurchaseBook purchaseBook = register(entity);
        logger.info("Book has been purchased with details {}", purchaseBook);

        boolean isAdminRevenueGenerated = adminRevenueService.createRevenueFromPurchaseBook(entity);

        if (!isAdminRevenueGenerated) {
            logger.warn("Failed to generate revenue for admin");
            throw new RevenueGenerationFailedException("Failed to generate Admin Revenue");
        }

        logger.info("Admin Revenue Generated Successfully");

        boolean isAuthorRevenueGenerated = authorRevenueService.createRevenueFromPurchaseBook(entity);

        if (!isAuthorRevenueGenerated) {
            logger.warn("Failed to generate revenue for author");
            throw new RevenueGenerationFailedException("Failed to generate Author Revenue");
        }

        logger.info("Author Revenue Generated Successfully");
        return purchaseBook;
    }

    @Override
    public double calculatePurchasePriceBook(PurchaseBookAmountCalculateRequest request) {
        Book book = bookService.get(request.bookId());

        return helper.calculateTotalAmount(book, request.quantity());
    }

    private StringBuilder buildInvalidRequestAmount(double totalPurchaseAmount, double requestedPurchaseAmount) {
        return new StringBuilder()
                .append("Invalid Purchase Book Amount")
                .append(" Actual Purchase Book Amount ")
                .append(totalPurchaseAmount)
                .append(" Purchase Book Requested Amount ")
                .append(requestedPurchaseAmount)
                .append(" Difference Amount is ")
                .append(Math.abs(totalPurchaseAmount - requestedPurchaseAmount));
    }

    private void populateRelationships(PurchaseBook entity, PurchaseBookRequest request) {
        logger.info("For Book Purchase Filling related data");

        Book book = bookService.get(request.bookId());

        entity.setBook(book);

        logger.debug("Book Info : {}", book);

        Users user = userService.get(userSession.getCurrentUserId());
        entity.setUser(user);

        logger.debug("User Info : {}", user);

        logger.info("Book Purchase related entities are added");
    }
}
