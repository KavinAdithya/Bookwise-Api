package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.dtos.subscription.response.BorrowBookSubscriptionDetail;

import java.time.LocalDateTime;

public record BorrowBookConfirmationDetail(
        long bookId,
        String title,
        String description,
        int availableQuantity,
        String authorName,
        String categoryName,
        String coverImageUrl,
        LocalDateTime dueDate,
        BorrowBookSubscriptionDetail subscription
) {
}
