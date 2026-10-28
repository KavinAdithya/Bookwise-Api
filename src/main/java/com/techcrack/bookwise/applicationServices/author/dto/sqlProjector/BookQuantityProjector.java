package com.techcrack.bookwise.applicationServices.author.dto.sqlProjector;

public interface BookQuantityProjector {
    String getIncomeType();
    long getBorrowBookCount();
    long getPurchaseBookCount();
}
