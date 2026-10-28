package com.techcrack.bookwise.applicationServices.purchasebook.helper;

import com.techcrack.bookwise.applicationServices.purchasebook.dto.response.PurchasePriceOfOrder;
import com.techcrack.bookwise.applicationServices.book.entity.Book;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;
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
