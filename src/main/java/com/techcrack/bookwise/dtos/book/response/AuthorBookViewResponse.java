package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.constans.enums.Status;

public record AuthorBookViewResponse (
    Long id,
    String title,
    String categoryName,
    String coverImageUrl,
    int totalCopies,
    int availableCopies,
    Status status
) {}