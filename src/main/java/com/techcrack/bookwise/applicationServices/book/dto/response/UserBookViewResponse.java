package com.techcrack.bookwise.applicationServices.book.dto.response;

public record UserBookViewResponse(
     Long id,
     String title,
     String authorName,
     String categoryName,
     int availableCopies,
     double purchasePrice,
     double borrowFee,
     String coverImageUrl) {
}