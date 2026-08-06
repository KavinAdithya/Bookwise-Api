package com.techcrack.bookwise.dtos.borrowbook.request;

import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;

public record FetchBorrowBookRequest(long borrowBookId) {
    public BorrowBookContext buildContext(long userId) {
        return new BorrowBookContext(borrowBookId, userId);
    }
}
