package com.techcrack.bookwise.dtos.author.sqlProjector;

import java.math.BigDecimal;

public interface AuthorRevenueProjector {
    String getIncomeType();
    BigDecimal getTotalRevenue();
    BigDecimal getRevenueClaimed();
    BigDecimal getRevenueNotClaimed();
}
