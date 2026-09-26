package com.techcrack.bookwise.dtos.borrowbook.response;

import com.techcrack.bookwise.constans.enums.BorrowStatus;

import java.time.LocalDateTime;

public record BorrowBookDetailView(
        long id,
        BookBasicInfo book,
        int borrowQuantity,
        LocalDateTime borrowedDate,
        LocalDateTime dueDate,
        LocalDateTime returnedAt,
        BorrowStatus borrowStatus,
        double totalAmountPaid
) {
}
