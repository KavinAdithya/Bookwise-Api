package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.entity.BorrowBook;

import java.util.List;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBook entity);
    BorrowBook getBorrowDetails(BorrowBookContext context);
    double calculateDueAmount(BorrowBookContext context);
    List<BorrowBook> getBorrowDetails(long userId);
    void returnBook(ReturnBookContext context);
}
