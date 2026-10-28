package com.techcrack.bookwise.applicationServices.purchasebook.dto.response;

public record PurchasePriceOfOrder(long bookId, long quantity, double singleBookAmount, double amount) {
}
