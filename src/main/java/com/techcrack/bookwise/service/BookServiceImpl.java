package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.abstractions.BookService;
import com.techcrack.bookwise.abstractions.CategoryService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.book.request.BookRegisterRequest;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.repository.BookRepository;
import com.techcrack.bookwise.utils.AbstractService;
import com.techcrack.bookwise.validations.BookServiceValidations;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends AbstractService<BookServiceImpl, BookRepository, BookServiceValidations>
                                implements BookService {
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository repo, AuthorService authorService, CategoryService categoryService, BookServiceValidations validations, CurrentUserService userSession) {
        super(BookServiceImpl.class, repo, validations, userSession);
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public Book register(Book entity) {
        logger.info("Book Registration Process started with Book Title {}", entity.getTitle());

        entity = repo.save(entity);

        logger.info("Book Registration completed successfully");

        return entity;
    }

    @Override
    @Transactional
    public void remove(long key) {
        repo.deleteById(key);
    }

    @Override
    @Transactional
    public Book update(Book entity) {
        return repo.save(entity);
    }

    @Override
    public Book get(long key) {
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(Book.class, "Book Not Found"));
    }

    @Override
    @Transactional
    public Book createBook(BookRegisterRequest request) {
        Book entity = request.buildBook();
        entity.initialize(userSession.getCurrentUserId());

        Errors errors = validations.validateBookDetails(entity);

        if (errors.hasErrors()) {
            logger.error("Invalid Book Details : {} ", errors.getData());
            throw new InvalidDataException("Invalid Book Details : " + errors.getData());
        }

        populateRelationships(entity, request);

        errors = validations.validateAuthor(entity);

        if (errors.hasErrors()) {
            logger.error("Invalid Author Details : {} ", errors.getData());
            throw new InvalidDataException("Invalid Author Details : " + errors.getData());
        }

        entity.setBookStatus(Status.PENDING);
        entity.setCommissionPercentage(ApplicationData.COMMISSION_PERCENTAGE);
        return register(entity);
    }

    private void populateRelationships(Book entity, BookRegisterRequest request) {

        Author author = authorService.get(userSession.getCurrentUserId());
        logger.debug("Author Info : {}", author);

        entity.setAuthor(author);
        logger.debug("Author successfully set to book Book : {}", entity);

        Category category = categoryService.getCategoryByName(request.getCategoryName());
        logger.debug("Category Info : {}", category);
        entity.setCategory(category);
    }

    @Override
    public boolean checkAvailability(long id, int quantity) {
        Book book = get(id);

        return book.isActive() && book.getAvailableCopies() >= quantity;
    }

    @Override
    @Transactional
    public boolean updateBookAvailability(long id, int quantity) {
        Book book = get(id);

        book.setAvailableCopies(book.getAvailableCopies() - quantity);
        logger.info("Book Quantity Updated successfully for {}", id);

        return true;
    }

    @Override
    public List<Book> getAllApprovedAndAvailableBooks() {
        return repo.findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(
                Status.APPROVED,
                0
        );
    }

    @Override
    public List<Book> getAllPendingBooks() {
        return repo.findAllByBookStatusAndAvailableCopiesGreaterThanAndIsActiveTrue(
            Status.PENDING,
            0
        );
    }

    @Override
    @Transactional
    public int approveAllBooks(List<Long> bookIds) {
        logger.info("Approving Books {}", bookIds);
        int rowsAffected = repo.changeStatusOfAllBooks(
                bookIds,
                true,
                Status.APPROVED,
                ApplicationData.HARD_CODED_CURRENT_ID,
                ApplicationData.getSystemDate(),
                ApplicationData.getSystemDate());

        logger.info("Books Approved for {}", rowsAffected);
        return rowsAffected;
    }

    @Transactional
    public int rejectAllBooks(List<Long> bookIds) {
        logger.info("Rejecting Books : {}", bookIds);
        int rowsAffected = repo.changeStatusOfAllBooks(bookIds, false, Status.REJECTED, ApplicationData.HARD_CODED_CURRENT_ID, ApplicationData.getSystemDate(), null);
        logger.info("Books Rejected successfully : {}", rowsAffected);
        return rowsAffected;
    }

    @Transactional
    public boolean updateBookQuantity(long bookId, int quantity) {
        return repo.updateBookQuantity(bookId, quantity, userSession.getCurrentUserId(), ApplicationData.getSystemDate()) >= 1;
    }

}