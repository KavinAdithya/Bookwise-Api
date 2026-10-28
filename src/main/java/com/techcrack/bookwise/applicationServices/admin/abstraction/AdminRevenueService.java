package com.techcrack.bookwise.applicationServices.admin.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.admin.entity.AdminRevenue;
import com.techcrack.bookwise.applicationServices.borrowbook.entity.BorrowBook;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;
import com.techcrack.bookwise.applicationServices.subscription.entity.Subscription;

public interface AdminRevenueService extends BasicCRUD<AdminRevenue> {
    boolean createRevenueFromSubscription(Subscription subscription);
    boolean createRevenueFromBorrowBook(BorrowBook borrowBook);
    boolean createRevenueFromPurchaseBook(PurchaseBook purchaseBook);
}
