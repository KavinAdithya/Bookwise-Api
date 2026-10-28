package com.techcrack.bookwise.applicationServices.purchasebook.dto.response;

import com.techcrack.bookwise.applicationServices.purchasebook.dto.context.PurchaseBookBasicInfo;

public record PurchasingBookDetailResponse(
        PurchaseBookBasicInfo book,
        PurchasePriceOfOrder price
) {
}
