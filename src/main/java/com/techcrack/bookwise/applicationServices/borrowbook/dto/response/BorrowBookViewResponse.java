package com.techcrack.bookwise.applicationServices.borrowbook.dto.response;

import com.techcrack.bookwise.constans.enums.BorrowStatus;

import java.math.BigDecimal;
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
        BigDecimal fineAmount
) {
}
