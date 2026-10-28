package com.techcrack.bookwise.applicationServices.book.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.book.repository.projection.BookCountProjection;
import com.techcrack.bookwise.constans.enums.BookStatus;
import com.techcrack.bookwise.applicationServices.book.dto.request.BookRegisterRequest;
import com.techcrack.bookwise.applicationServices.book.dto.response.AdminViewBookResponse;
import com.techcrack.bookwise.applicationServices.book.dto.response.AuthorBookViewResponse;
import com.techcrack.bookwise.applicationServices.book.dto.response.UserBookDetailViewResponse;
import com.techcrack.bookwise.applicationServices.book.dto.response.UserBookViewResponse;
import com.techcrack.bookwise.applicationServices.book.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService extends BasicCRUD<Book> {
    boolean checkBookAvailability(long id, int quantity);
    boolean updateBookAvailability(long id, int quantity);
    List<UserBookViewResponse> getAllPublishedBooks();
    UserBookDetailViewResponse getBookDetail(long bookId);
    int rejectAllBooks(List<Long> bookIds);
    int approveAllBooks(List<Long> bookIds);
    Book createBook(BookRegisterRequest request, MultipartFile coverImage);
    boolean updateBookQuantity(long bookId, int quantity);
    List<AuthorBookViewResponse> getAuthorBooksAll();
    Book getBookById(long id);
    List<AdminViewBookResponse> getAllBooksForAdmin(BookStatus status);
    BookCountProjection getBookCountDetails();
}
