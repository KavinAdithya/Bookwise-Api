package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final Logger logger;

    public BookServiceImpl(BookRepository repo, AuthorService authorService, CategoryService categoryService) {
        this.repo = repo;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.logger = LoggerFactory.getLogger(BookServiceImpl.class);
    }

    @Override
    public Book register(Book entity) {
        logger.info("Book Registration Process started with Book Title {}", entity.getTitle());

        fillRelatedEntity(entity);

        Errors errors = validateBookDetails(entity);

        if (errors.hasErrors()) {
            logger.error("Invalid Book Details : {} ", errors.getData());
            throw new InvalidDataException("Invalid Book Details : " + errors.getData());
        }

        entity = repo.save(entity);

        logger.info("Book Registration completed successfully");

        return entity;
    }

    private void fillRelatedEntity(Book entity) {

        Author author = authorService.get(entity.getAuthor().getId());
        logger.debug("Author Info : {}", author);

        entity.setAuthor(author);
        logger.debug("Author successfully set to book Book : {}", entity);

        Category category = categoryService.getCategoryByName(entity.getCategory().getName());
        logger.debug("Category Info : {}", category);
        entity.setCategory(category);

        entity.setCommissionPercentage(ApplicationData.COMMISSION_PERCENTAGE);
    }

    private Errors validateBookDetails(Book entity) {
        logger.info("Validating book details started");

        Errors errors = new Errors();

        if (!authorService.isAuthorValid(entity.getAuthor())) {
            logger.error("Invalid Author {}", entity.getAuthor());
            errors.addErrorMessage("Author : Author is Not Authorized to launch a book");
        }

        logger.info("Author data is validated successfully");

        if (entity.getTitle() == null || entity.getTitle().isEmpty()) {
            logger.error("Invalid Book Title");
            errors.addErrorMessage("Invalid Book Title : Ensure title is not empty.");
        }

        if (entity.getISBN() == null || entity.getISBN().length() != ApplicationData.ISBN_LENGTH) {
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

    @Override
    public void remove(long key) {

    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public Book get(long key) {
        return null;
    }
}
