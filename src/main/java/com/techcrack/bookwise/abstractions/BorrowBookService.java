package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.entity.BorrowBook;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBook entity);
    BorrowBook getBorrowDetails(BorrowBookContext context);
}
