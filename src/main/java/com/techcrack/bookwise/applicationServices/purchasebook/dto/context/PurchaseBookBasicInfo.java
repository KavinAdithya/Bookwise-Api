package com.techcrack.bookwise.applicationServices.purchasebook.dto.context;

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
