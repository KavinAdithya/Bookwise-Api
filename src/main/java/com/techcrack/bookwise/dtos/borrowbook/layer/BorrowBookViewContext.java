package com.techcrack.bookwise.dtos.borrowbook.layer;

import com.techcrack.bookwise.constans.enums.BorrowStatus;

import java.time.LocalDateTime;

public record BorrowBookViewContext(
        long borrowBookId,
        long bookId,
        String coverImageUrl,
        String bookTitle,
        String authorName,
        int borrowedQuantity,
        LocalDateTime borrowedDate,
        LocalDateTime dueDate,
        LocalDateTime returnedAt,
        BorrowStatus borrowStatus
) {
}
