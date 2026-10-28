package com.techcrack.bookwise.applicationServices.borrowbook.dto.request;

import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.BorrowBookContext;

public record FetchBorrowBookRequest(long borrowBookId) {
    public BorrowBookContext buildContext(long userId) {
        return new BorrowBookContext(borrowBookId, userId);
    }
}
