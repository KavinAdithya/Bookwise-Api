package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.constans.enums.BookStatus;

public record AuthorBookViewResponse (
        Long id,
        String title,
        String categoryName,
        String coverImageUrl,
        int totalCopies,
        int availableCopies,
        BookStatus status
) {}