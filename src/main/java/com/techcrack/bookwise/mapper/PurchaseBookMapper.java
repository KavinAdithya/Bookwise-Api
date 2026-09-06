package com.techcrack.bookwise.mapper;

import com.techcrack.bookwise.dtos.purchasebook.response.PurchaseBookResponse;
import com.techcrack.bookwise.entity.PurchaseBook;
import org.springframework.stereotype.Component;

@Component
public class PurchaseBookMapper {
    public PurchaseBookResponse mapToPurchaseBookResponse(PurchaseBook purchaseBook) {
        return new PurchaseBookResponse(purchaseBook.getId(),
                purchaseBook.getBook().getId(),
                purchaseBook.getQuantity(),
                purchaseBook.getTotalAmount());
    }
}
