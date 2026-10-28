package com.techcrack.bookwise.applicationServices.book.dto.response;

public record UserBookDetailViewResponse(
        Long id,
        String title,
        String ISBN,
        String description,
        String categoryName,
        String authorName,
        int availableCopies,
        int totalCopies,
        double borrowFee,
        double purchasePrice,
        String coverImageUrl
) {
}
