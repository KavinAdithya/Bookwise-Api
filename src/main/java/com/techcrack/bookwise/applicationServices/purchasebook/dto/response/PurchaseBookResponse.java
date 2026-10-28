package com.techcrack.bookwise.applicationServices.purchasebook.dto.response;


public record PurchaseBookResponse(long purchaseId, long bookId, int quantity, double totalAmount) {
}
