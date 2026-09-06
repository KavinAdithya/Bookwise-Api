package com.techcrack.bookwise.dtos.purchasebook.response;


public record PurchaseBookResponse(long purchaseId, long bookId, int quantity, double totalAmount) {
}
