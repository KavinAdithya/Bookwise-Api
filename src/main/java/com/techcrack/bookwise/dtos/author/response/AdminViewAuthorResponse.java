package com.techcrack.bookwise.dtos.author.response;

import com.techcrack.bookwise.constans.enums.Status;

import java.time.LocalDateTime;

public record AdminViewAuthorResponse(
        long id,
        String authorName,
        Status status,
        String email,
        LocalDateTime createdAt
) {
}