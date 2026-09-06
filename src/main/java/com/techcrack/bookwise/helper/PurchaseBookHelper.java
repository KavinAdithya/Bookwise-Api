package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.entity.Book;
import com.techcrack.bookwise.entity.PurchaseBook;
import org.springframework.stereotype.Component;

@Component
public class PurchaseBookHelper {
    public double calculateTotalAmountFromPurchaseBook(PurchaseBook entity) {
        if (entity == null || entity.getBook() == null) {
            return 0.0;
        }

       return calculateTotalAmount(entity.getBook(), entity.getQuantity());
    }

    public double calculateTotalAmount(Book book, int quantity) {
        double bookPrice = book.getPurchasePrice();
        return bookPrice * quantity;
    }
}
