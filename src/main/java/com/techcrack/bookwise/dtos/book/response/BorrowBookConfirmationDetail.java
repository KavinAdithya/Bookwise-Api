package com.techcrack.bookwise.dtos.book.response;

import com.techcrack.bookwise.dtos.subscription.response.BorrowBookSubscriptionDetail;
import com.techcrack.bookwise.dtos.subscription.response.SubscriptionDetails;

public record BorrowBookConfirmationDetail(
        long bookId,
        String title,
        String authorName,
        BorrowBookSubscriptionDetail subscription
) {
}
