package com.techcrack.bookwise.dtos.author.sqlProjector;

public interface BookQuantityProjector {
    String getIncomeType();
    long getBorrowBookCount();
    long getPurchaseBookCount();
}
