package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.dtos.purchasebook.request.PurchaseBookRequest;
import com.techcrack.bookwise.entity.PurchaseBook;

public interface PurchaseBookService extends BasicCRUD<PurchaseBook> {
    PurchaseBook purchaseBook(PurchaseBookRequest request);
}
