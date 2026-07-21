package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.PurchaseBookService;
import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.repository.PurchaseBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PurchaseBookServiceImpl implements PurchaseBookService {
    private final PurchaseBookRepository repo;
    private final BookService bookService;
    private final UserService userService;
    private final Logger logger;

    public PurchaseBookServiceImpl(PurchaseBookRepository repo, BookService bookService, UserService userService) {
        this.repo = repo;
        this.bookService = bookService;
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(PurchaseBookServiceImpl.class);
    }

    @Override
    public PurchaseBook register(PurchaseBook entity) {
        logger.info("Book Purchase Process Started for User {} Book {}", entity.getUser().getId(), entity.getBook().getId());

        if (!bookService.checkAvailability(entity.getBook().getId(), entity.getQuantity())) {
            logger.error("Requested book stock is not available");
            throw new InvalidDataException("Book Quantity not available as you requested Quantity : "+ entity.getQuantity());
        }

        logger.info("Book Quantity Available");

        boolean res = bookService.updateBookAvailability(entity.getBook().getId(), entity.getQuantity());

        if (!res) {
            throw new InvalidDataException("Failed to update book count");
        }
        
        populateRelationships(entity);

        entity = repo.save(entity);

        logger.info("Book has been successfully purchased.");

        return entity;
    }

    private void populateRelationships(PurchaseBook entity) {
        logger.info("For Book Purchase Filling related data");

        Book book = bookService.get(entity.getBook().getId());

        entity.setBook(book);

        logger.debug("Book Info : {}", book);

        Users user = userService.get(entity.getUser().getId());
        entity.setUser(user);

        logger.debug("User Info : {}", user);

        logger.info("Book Purchase related entities are added");
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
}
