package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.constans.enums.BookStatus;

public record AdminViewBookDetailResponse(
        Long id,
        String title,
        String ISBN,
        String description,
        String authorName,
        String categoryName,
        int totalCopies,
        int availableCopies,
        double borrowFee,
        double purchasePrice,
        BookStatus status,
        String coverImageUrl
) {
}
