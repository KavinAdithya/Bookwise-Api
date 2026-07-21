package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.BookRepository;
import com.techcrack.bookwise.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookServiceValidations {
    private final BookRepository repo;
    private final AuthorService authorService;
    private final Logger logger;

    public BookServiceValidations(BookRepository repo, AuthorService authorService) {
        this.repo = repo;
        this.authorService = authorService;
        this.logger = LoggerFactory.getLogger(BookServiceValidations.class);
    }

    public Errors validateBookDetails(Book entity) {
        logger.info("Validating book details started");

        Errors errors = new Errors();


        if (!validateTitle(entity.getTitle())) {
            logger.error("Invalid Book Title");
            errors.addErrorMessage("Invalid Book Title : Ensure title is not empty.");
        }

        if (!validateISBN(entity.getISBN())) {
            logger.error("Invalid ISBN Number Length");
            errors.addErrorMessage("Invalid ISBN Number : Please make sure ISBN number is " + ApplicationData.ISBN_LENGTH + " length");
        }

        if (entity.getTotalCopies() <= 0) {
            logger.error("Invalid Total Copies");
            errors.addErrorMessage("Invalid Total Copies : Please make sure Total copies is greater than zero.");
        }

        if (entity.getAvailableCopies() <= 0) {
            logger.error("Invalid Available Copies");
            errors.addErrorMessage("Invalid Available Copies : Please make sure Available copies is greater than zero.");
        }

        if (entity.getPurchasePrice() <= 0) {
            logger.error("Invalid Purchase price");
            errors.addErrorMessage("Invalid Purchase price : Please make sure Purchase price is  greater than zero.");
        }

        if (entity.getBorrowFee() <= 0) {
            logger.error("Invalid Borrow Fee");
            errors.addErrorMessage("Invalid Borrow Fee : Please make sure Borrow fee is greater than zero.");
        }

        logger.info("Book Data Validation Process Done.");
        return errors;
    }

    public boolean validateISBN(String ISBN) {
        return ISBN != null && ISBN.length() == ApplicationData.ISBN_LENGTH
                    && !repo.existsByISBN(ISBN);
    }

    public boolean validateTitle(String title) {
        return title != null && !title.isEmpty()
                && !repo.existsByTitle(title);
    }

    public Errors validateAuthor(Book entity) {
        Errors errors = new Errors();

        if (!authorService.isAuthorValid(entity.getAuthor())) {
            logger.error("Invalid Author {}", entity.getAuthor());
            errors.addErrorMessage("Author : Author is Not Authorized to launch a book");
        }

        logger.info("Author data is validated successfully");

        return errors;
    }
}
