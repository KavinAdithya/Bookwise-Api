package com.techcrack.bookwise.dtos.purchasebook.response;

import com.techcrack.bookwise.dtos.purchasebook.context.PurchaseBookBasicInfo;

import java.time.LocalDateTime;

public record PurchasedBookDetailResponse (
        long id,
        PurchaseBookBasicInfo book,
        int quantity,
        LocalDateTime purchasedDate,
        double totalPurchasedAmount
) {
}
