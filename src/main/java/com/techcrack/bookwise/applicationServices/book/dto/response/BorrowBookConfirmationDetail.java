package com.techcrack.bookwise.applicationServices.book.dto.response;

import com.techcrack.bookwise.applicationServices.subscription.dto.response.BorrowBookSubscriptionDetail;

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
