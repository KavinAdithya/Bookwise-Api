package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.AdminRevenue;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.entity.PurchaseBook;
import com.techcrack.bookwise.entity.Subscription;

public interface AdminRevenueService extends BasicCRUD<AdminRevenue>{
    boolean createRevenueFromSubscription(Subscription subscription);
    boolean createRevenueFromBorrowBook(BorrowBook borrowBook);
    boolean createRevenueFromPurchaseBook(PurchaseBook purchaseBook);
}
