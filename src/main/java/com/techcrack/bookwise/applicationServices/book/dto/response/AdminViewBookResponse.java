package com.techcrack.bookwise.applicationServices.book.dto.response;

import com.techcrack.bookwise.constans.enums.BookStatus;

public record AdminViewBookResponse(
        Long id,
        String title,
        int totalCopies,
        int availableCopies,
        String authorName,
        String categoryName,
        BookStatus status,
        String coverImageUrl) {
}
