package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.book.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.entity.BorrowBook;

import java.util.List;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBookRequest request);
    BorrowBook getBorrowBookById(long borrowBookId);
    double calculateDueAmount(long borrowBookId);
    List<BorrowBook> getAllBorrowBooks();
    void returnBook(ReturnBookContext context);
    BorrowBookConfirmationDetail computeBorrowBookConfirmationDetails(BorrowBookRequest request);
}
