package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Book;

public interface BookService extends BasicCRUD<Book> {
    boolean checkAvailability(long id, int quantity);
    boolean updateBookAvailability(long id, int quantity);
}
