package com.techcrack.bookwise.dtos.borrowbook.response;

import com.techcrack.bookwise.constans.enums.BorrowStatus;

import java.time.LocalDateTime;

public record BorrowBookViewResponse(
        long borrowBookId,
        long bookId,
        String coverImageUrl,
        String bookTitle,
        String authorName,
        int borrowedQuantity,
        LocalDateTime borrowedDate,
        LocalDateTime dueDate,
        LocalDateTime returnedAt,
        BorrowStatus borrowStatus,
        double fineAmount
) {
}
