package com.techcrack.bookwise.dtos.purchasebook.response;

import java.time.LocalDateTime;

public record PurchaseBookViewResponse(
        long id,
        String title,
        String categoryName,
        String authorName,
        double purchasedPrice,
        LocalDateTime purchasedAt,
        String coverImageUrl
) {
}
