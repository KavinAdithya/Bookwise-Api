package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.book.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.dtos.borrowbook.layer.DueAmountDetails;
import com.techcrack.bookwise.dtos.borrowbook.layer.ReturnBookContext;
import com.techcrack.bookwise.dtos.borrowbook.request.BorrowBookRequest;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookDetailView;
import com.techcrack.bookwise.dtos.borrowbook.response.BorrowBookViewResponse;
import com.techcrack.bookwise.dtos.borrowbook.response.ReturnBorrowBookDetails;
import com.techcrack.bookwise.entity.BorrowBook;

import java.util.List;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBookRequest request);
    BorrowBook getBorrowBookById(long borrowBookId);
    DueAmountDetails calculateDueAmount(long borrowBookId);
    ReturnBorrowBookDetails computeReturnDetails(long borrowBookId);
    DueAmountDetails calculateDueAmount(BorrowBook entity);
    List<BorrowBookViewResponse> getAllBorrowBooks();
    void returnBook(ReturnBookContext context);
    BorrowBookDetailView getBorrowBookDetailView(long borrowBookId);
    BorrowBookConfirmationDetail computeBorrowBookConfirmationDetails(BorrowBookRequest request);
}
