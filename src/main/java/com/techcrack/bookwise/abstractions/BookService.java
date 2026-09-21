package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.constans.enums.BookStatus;
import com.techcrack.bookwise.dtos.book.request.BookRegisterRequest;
import com.techcrack.bookwise.dtos.book.response.AdminViewBookResponse;
import com.techcrack.bookwise.dtos.book.response.AuthorBookViewResponse;
import com.techcrack.bookwise.dtos.book.response.UserBookDetailViewResponse;
import com.techcrack.bookwise.dtos.book.response.UserBookViewResponse;
import com.techcrack.bookwise.entity.Book;
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
}
