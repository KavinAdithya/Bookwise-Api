package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.BorrowBook;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBook entity);
}
