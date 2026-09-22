package com.techcrack.bookwise.dtos.subscription.response;

import com.techcrack.bookwise.constans.enums.Subscriptions;

import java.time.LocalDateTime;

public record BorrowBookSubscriptionDetail(
        long id,
        Subscriptions plan,
        LocalDateTime endDate,
        long availableBorrowBookCount,
        long totalBorrowBookCount,
        double planFineAmount
) {
}
