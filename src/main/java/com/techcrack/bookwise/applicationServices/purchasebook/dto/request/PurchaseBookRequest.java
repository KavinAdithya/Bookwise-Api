package com.techcrack.bookwise.applicationServices.purchasebook.dto.request;

public record PurchaseBookRequest(long bookId, int quantity, double purchaseAmount) {
}
