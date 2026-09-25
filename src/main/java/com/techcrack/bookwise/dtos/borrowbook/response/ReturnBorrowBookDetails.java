package com.techcrack.bookwise.dtos.borrowbook.response;

import com.techcrack.bookwise.dtos.borrowbook.layer.DueAmountDetails;

import java.time.LocalDateTime;

public record ReturnBorrowBookDetails (
    long borrowBookId,
    long bookId,
    String title,
    String description,
    int quantity,
    String categoryName,
    String authorName,
    LocalDateTime borrowedDate,
    LocalDateTime dueDate,
    String coverImageUrl,
    DueAmountDetails dueAmountDetails) {
}
