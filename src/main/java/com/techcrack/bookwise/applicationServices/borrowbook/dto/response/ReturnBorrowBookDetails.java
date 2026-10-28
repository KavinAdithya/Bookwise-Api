package com.techcrack.bookwise.applicationServices.borrowbook.dto.response;

import com.techcrack.bookwise.applicationServices.borrowbook.dto.layer.DueAmountDetails;

import java.time.LocalDateTime;

public record ReturnBorrowBookDetails (
    long id,
    BookBasicInfo book,
    int quantity,
    LocalDateTime borrowedDate,
    LocalDateTime dueDate,
    DueAmountDetails dueAmountDetails) {
}
