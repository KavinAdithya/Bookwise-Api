package com.techcrack.bookwise.dtos.borrowbook.response;

import com.techcrack.bookwise.dtos.borrowbook.layer.DueAmountDetails;

import java.time.LocalDateTime;

public record ReturnBorrowBookDetails (
    long id,
    BookBasicInfo book,
    int quantity,
    LocalDateTime borrowedDate,
    LocalDateTime dueDate,
    DueAmountDetails dueAmountDetails) {
}
