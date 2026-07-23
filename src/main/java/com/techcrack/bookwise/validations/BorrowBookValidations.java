package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.utils.BaseLoggerService;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookValidations extends BaseLoggerService<BorrowBookValidations, BookService> {
    private final SubscriptionService subscriptionService;
    public BorrowBookValidations(BookService service, SubscriptionService subscriptionService) {
        super(BorrowBookValidations.class, service);
        this.subscriptionService = subscriptionService;
    }

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

        if (hasNoError && !service.checkAvailability(entity.getBook().getId(), entity.getQuantity())) {
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

        return errors;
    }
}
