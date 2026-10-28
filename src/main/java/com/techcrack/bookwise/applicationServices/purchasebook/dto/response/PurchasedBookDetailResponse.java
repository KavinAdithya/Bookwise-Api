package com.techcrack.bookwise.applicationServices.purchasebook.dto.response;

import com.techcrack.bookwise.applicationServices.purchasebook.dto.context.PurchaseBookBasicInfo;

import java.time.LocalDateTime;

public record PurchasedBookDetailResponse (
        long id,
        PurchaseBookBasicInfo book,
        int quantity,
        LocalDateTime purchasedDate,
        double totalPurchasedAmount
) {
}
