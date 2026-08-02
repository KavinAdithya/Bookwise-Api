package com.techcrack.bookwise.dtos.borrowbook.request;

import com.techcrack.bookwise.dtos.borrowbook.layer.BorrowBookContext;

import java.time.LocalDateTime;

public record FetchBorrowBookRequest(long bookId, LocalDateTime borrowDate) {
    public BorrowBookContext buildContext(long userId) {
        return new BorrowBookContext(bookId, userId, borrowDate);
    }
}
