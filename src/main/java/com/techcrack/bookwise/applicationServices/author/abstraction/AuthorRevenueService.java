package com.techcrack.bookwise.applicationServices.author.abstraction;

import com.techcrack.bookwise.abstractions.BasicCRUD;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummary;
import com.techcrack.bookwise.applicationServices.author.dto.context.AuthorRevenueSummaryRequest;
import com.techcrack.bookwise.applicationServices.author.entity.AuthorRevenue;
import com.techcrack.bookwise.applicationServices.borrowbook.entity.BorrowBook;
import com.techcrack.bookwise.applicationServices.purchasebook.entity.PurchaseBook;

public interface AuthorRevenueService extends BasicCRUD<AuthorRevenue> {
    boolean createRevenueFromBorrowBook(BorrowBook borrowBook);
    boolean createRevenueFromPurchaseBook(PurchaseBook purchaseBook);
    AuthorRevenueSummary computeAuthorRevenueSummary(AuthorRevenueSummaryRequest request);
}
