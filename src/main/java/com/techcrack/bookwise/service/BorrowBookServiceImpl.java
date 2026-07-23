package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.BorrowBookService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.BorrowBookRepository;
import com.techcrack.bookwise.utils.BaseLoggerRepoValidation;
import com.techcrack.bookwise.validations.BorrowBookValidations;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BorrowBookServiceImpl extends BaseLoggerRepoValidation<BorrowBookServiceImpl, BorrowBookRepository, BorrowBookValidations>
                                    implements BorrowBookService {
    private final UserService userService;
    private final BookService bookService;

    public BorrowBookServiceImpl(BorrowBookRepository repo, BorrowBookValidations validations, UserService userService, BookService bookService) {
       super(BorrowBookServiceImpl.class, repo, validations);
       this.userService = userService;
       this.bookService = bookService;
    }

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

        return register(entity);
    }

    public void setBorrowDetails(BorrowBook borrowBook) {

    }

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

    @Override
    @Transactional
    public BorrowBook register(BorrowBook entity) {
        return repo.save(entity);
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public BorrowBook update(BorrowBook entity) {
        return null;
    }

    @Override
    public BorrowBook get(long key) {
        return null;
    }
}
