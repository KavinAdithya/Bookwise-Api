package com.techcrack.bookwise.dtos.purchasebook.response;

public record PurchasePriceOfOrder(long bookId, long quantity, double singleBookAmount, double amount) {
}
