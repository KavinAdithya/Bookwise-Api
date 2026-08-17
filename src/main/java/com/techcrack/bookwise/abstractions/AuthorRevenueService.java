package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.entity.AuthorRevenue;
import com.techcrack.bookwise.entity.BorrowBook;

public interface AuthorRevenueService extends BasicCRUD<AuthorRevenue> {
    boolean createFromBorrowBook(BorrowBook borrowBook);
}
