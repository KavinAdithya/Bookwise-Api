package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.dtos.purchasebook.response.PurchasePriceOfOrder;
import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.PurchaseBook;
import org.springframework.stereotype.Component;

@Component
public class PurchaseBookHelper {
    public PurchasePriceOfOrder calculateTotalAmountFromPurchaseBook(PurchaseBook entity) {
        if (entity == null || entity.getBook() == null) {
            return null;
        }

       return calculateTotalAmount(entity.getBook(), entity.getQuantity());
    }

    public PurchasePriceOfOrder calculateTotalAmount(Book book, int quantity) {
        double bookPrice = book.getPurchasePrice();
        double price = bookPrice * quantity;

        return new PurchasePriceOfOrder(book.getId(), quantity, bookPrice, price);
    }
}
