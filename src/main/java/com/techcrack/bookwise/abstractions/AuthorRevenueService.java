package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.AuthorRevenue;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.PurchaseBook;

public interface AuthorRevenueService extends BasicCRUD<AuthorRevenue> {
    boolean createRevenueFromBorrowBook(BorrowBook borrowBook);
    boolean createRevenueFromPurchaseBook(PurchaseBook purchaseBook);
}
