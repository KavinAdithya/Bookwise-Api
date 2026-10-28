package com.techcrack.bookwise.applicationServices.book.dto.response;

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