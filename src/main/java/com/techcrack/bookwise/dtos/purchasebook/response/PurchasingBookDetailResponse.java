package com.techcrack.bookwise.dtos.purchasebook.response;

import com.techcrack.bookwise.dtos.purchasebook.context.PurchaseBookBasicInfo;

public record PurchasingBookDetailResponse(
        PurchaseBookBasicInfo book,
        PurchasePriceOfOrder price
) {
}
