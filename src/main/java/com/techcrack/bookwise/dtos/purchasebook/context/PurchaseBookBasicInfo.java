package com.techcrack.bookwise.dtos.purchasebook.context;

public record PurchaseBookBasicInfo(
        long id,
        String title,
        String description,
        int quantity,
        String categoryName,
        String authorName,
        String coverImageUrl
) {
}
