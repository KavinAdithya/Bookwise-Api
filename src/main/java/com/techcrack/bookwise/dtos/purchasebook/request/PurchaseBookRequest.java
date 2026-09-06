package com.techcrack.bookwise.dtos.purchasebook.request;

public record PurchaseBookRequest(long bookId, int quantity, double purchaseAmount) {
}
