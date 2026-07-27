package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.CategoryService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.BookRepository;
import com.techcrack.bookwise.utils.BaseLoggerRepoValidation;
import com.techcrack.bookwise.utils.BaseLoggerRepository;
import com.techcrack.bookwise.validations.BookServiceValidations;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends BaseLoggerRepoValidation<BookServiceImpl, BookRepository, BookServiceValidations>
                                implements BookService {

    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository repo, AuthorService authorService, CategoryService categoryService, BookServiceValidations validations) {
        super(BookServiceImpl.class, repo, validations);
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public Book register(Book entity) {
        logger.info("Book Registration Process started with Book Title {}", entity.getTitle());

        Errors errors = validations.validateBookDetails(entity);

        if (errors.hasErrors()) {
            logger.error("Invalid Book Details : {} ", errors.getData());
            throw new InvalidDataException("Invalid Book Details : " + errors.getData());
        }

        populateRelationships(entity);

        errors = validations.validateAuthor(entity);

        if (errors.hasErrors()) {
            logger.error("Invalid Author Details : {} ", errors.getData());
            throw new InvalidDataException("Invalid Author Details : " + errors.getData());
        }

        entity = repo.save(entity);

        logger.info("Book Registration completed successfully");

        return entity;
    }

    private void populateRelationships(Book entity) {

        Author author = authorService.get(entity.getAuthor().getId());
        logger.debug("Author Info : {}", author);

        entity.setAuthor(author);
        logger.debug("Author successfully set to book Book : {}", entity);

        Category category = categoryService.getCategoryByName(entity.getCategory().getName());
        logger.debug("Category Info : {}", category);
        entity.setCategory(category);

        entity.setCommissionPercentage(ApplicationData.COMMISSION_PERCENTAGE);
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
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(Book.class, "Book Not Found"));
    }

    @Override
    public boolean checkAvailability(long id, int quantity) {
        Book book = get(id);

        return book.isActive() && book.getAvailableCopies() >= quantity;
    }

    @Transactional
    @Override
    public boolean updateBookAvailability(long id, int quantity) {
        Book book = get(id);

        book.setAvailableCopies(book.getAvailableCopies() - quantity);
        logger.info("Book Quantity Updated successfully for {}", id);

        return true;
    }

    @Override
    @Transactional
    public List<Book> getAllApprovedAndAvailableBooks() {
        return repo.findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(
                Status.APPROVED,
                0
        );
    }

    @Transactional
    @Override
    public List<Book> getAllPendingBooks() {
        return repo.findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(
            Status.PENDING,
            0
        );
    }

    @Transactional
    public int approveAllBooks(List<Long> bookIds) {
        logger.info("Approving Books {}", bookIds);
        int rowsAffected = repo.changeStatusOfAllBooks(
                bookIds,
                true,
                Status.APPROVED,
                ApplicationData.HARD_CODED_CURRENT_ID,
                ApplicationData.SYSTEM_DATE);

        logger.info("Books Approved for {}", rowsAffected);
        return rowsAffected;
    }

    @Transactional
    public int rejectAllBooks(List<Long> bookIds) {
        logger.info("Rejecting Books : {}", bookIds);
        int rowsAffected = repo.changeStatusOfAllBooks(bookIds, false, Status.REJECTED, ApplicationData.HARD_CODED_CURRENT_ID, ApplicationData.SYSTEM_DATE);
        logger.info("Books Rejected successfully : {}", rowsAffected);
        return rowsAffected;
    }


}
