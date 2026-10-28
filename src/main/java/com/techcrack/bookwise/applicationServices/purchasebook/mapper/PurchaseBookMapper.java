package com.techcrack.bookwise.applicationServices.purchasebook.mapper;

import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchaseBookResponse;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;
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
