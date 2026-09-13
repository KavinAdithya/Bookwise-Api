package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.book.request.BookRegisterRequest;
import com.techcrack.bookwise.dtos.book.response.AuthorBookViewResponse;
import com.techcrack.bookwise.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService extends BasicCRUD<Book> {
    boolean checkAvailability(long id, int quantity);
    boolean updateBookAvailability(long id, int quantity);
    List<Book> getAllApprovedAndAvailableBooks();
    List<Book> getAllPendingBooks();
    int rejectAllBooks(List<Long> bookIds);
    int approveAllBooks(List<Long> bookIds);
    Book createBook(BookRegisterRequest request, MultipartFile coverImage);
    boolean updateBookQuantity(long bookId, int quantity);
    List<AuthorBookViewResponse> getAuthorBooksAll();
}
