package com.techcrack.bookwise.applicationServices.subscription.dto.response;

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
