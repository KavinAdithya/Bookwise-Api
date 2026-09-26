package com.techcrack.bookwise.dtos.borrowbook.response;

public record BookBasicInfo(
        long id,
        String title,
        String description,
        String categoryName,
        String authorName,
        String coverImageUrl
) {
}
