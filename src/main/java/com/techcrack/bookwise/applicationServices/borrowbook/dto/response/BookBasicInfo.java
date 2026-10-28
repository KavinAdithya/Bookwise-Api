package com.techcrack.bookwise.applicationServices.borrowbook.dto.response;

public record BookBasicInfo(
        long id,
        String title,
        String description,
        String categoryName,
        String authorName,
        String coverImageUrl
) {
}
