package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Book;

import java.util.List;

public interface BookService extends BasicCRUD<Book> {
    boolean checkAvailability(long id, int quantity);
    boolean updateBookAvailability(long id, int quantity);
    List<Book> getAllApprovedAndAvailableBooks();
    List<Book> getAllPendingBooks();
    void rejectAllBooks(List<Long> bookIds);
    void approveAllBooks(List<Long> bookIds);
}
