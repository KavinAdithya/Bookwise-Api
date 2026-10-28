package com.techcrack.bookwise.applicationServices.borrowbook.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.book.dto.response.BorrowBookConfirmationDetail;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.DueAmountDetails;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.ReturnBookContext;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.request.BorrowBookRequest;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.response.BorrowBookDetailView;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.response.BorrowBookViewResponse;
import com.techcrack.bookwise.applicationServices.borrowbook.dto.response.ReturnBorrowBookDetails;
import com.techcrack.bookwise.applicationServices.borrowbook.entity.BorrowBook;

import java.util.List;

public interface BorrowBookService extends BasicCRUD<BorrowBook> {
    BorrowBook borrowBook(BorrowBookRequest request);
    BorrowBook getBorrowBookById(long borrowBookId);
    DueAmountDetails calculateDueAmount(long borrowBookId);
    ReturnBorrowBookDetails computeReturnDetails(long borrowBookId);
    DueAmountDetails calculateDueAmount(BorrowBook entity);
    List<BorrowBookViewResponse> getAllBorrowBooksBasedOnCurrentUser();
    void returnBook(ReturnBookContext context);
    BorrowBookDetailView getBorrowBookDetailView(long borrowBookId);
    BorrowBookConfirmationDetail computeBorrowBookConfirmationDetails(BorrowBookRequest request);
}
