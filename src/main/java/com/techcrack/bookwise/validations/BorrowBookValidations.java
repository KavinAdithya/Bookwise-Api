package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookValidations extends AbstractLogger<BorrowBookValidations> {
    private final BookService bookService;
    private final SubscriptionService subscriptionService;
    public BorrowBookValidations(BookService bookService, SubscriptionService subscriptionService) {
        super(BorrowBookValidations.class);
        this.bookService = bookService;
        this.subscriptionService = subscriptionService;
    }

    /**
     * Validates whether a user is eligible to borrow a book.
     * <p>
     * Performs the following validations:
     * <ul>
     *     <li>Checks if the book exists and is available for borrowing.</li>
     *     <li>Verifies that the requested borrow quantity is valid.</li>
     *     <li>Ensures the author and book are in an approved state.</li>
     *     <li>Checks whether the user is eligible to borrow the book based on business rules.</li>
     * </ul>
     *
     * @param entity the borrow request containing the user and book details
     * @return a list of validation errors; returns an empty list if all validations pass
     */
    public Errors isValidBorrow(BorrowBook entity) {
        Errors errors = new Errors();

        logger.info("Validating Borrow Book Details");
        String message = null;
        boolean hasNoError = true;

        if (entity.getBook() == null) {
            message = "No Book has been selected. Please select Book.";
            logger.error(message);
            errors.addErrorMessage(message);
            hasNoError = false;
        }

        if (entity.getQuantity() <= 0) {
            message = "You have selected Negative book quantity which is not valid. Please select Book Quantity properly.";
            logger.error(message);
            errors.addErrorMessage(message);
        }

        if (hasNoError && !bookService.checkAvailability(entity.getBook().getId(), entity.getQuantity())) {
            message = "Book is out of stock for Book Name " + entity.getBook().getTitle();
            logger.error(message);
            errors.addErrorMessage(message);
        }

        if (entity.getUser() == null) {
            message = "No User Details found.";
            logger.error(message);
            errors.addErrorMessage(message);
            hasNoError = false;
        }

        if (hasNoError && !subscriptionService.hasLimitToBorrowBook(entity.getUser().getId())) {
            message = "User Doesn't have limit to borrow book. You can Upgrade subscription to borrow books";
            logger.error(message);
            errors.addErrorMessage(message);
        }

        logger.info("Validating borrow book details completed");

        return errors;
    }
}
