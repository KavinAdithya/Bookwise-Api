package com.techcrack.bookwise.applicationServices.author.dto.sqlProjector;

import java.math.BigDecimal;

public interface AuthorRevenueProjector {
    String getIncomeType();
    BigDecimal getTotalRevenue();
    BigDecimal getRevenueClaimed();
    BigDecimal getRevenueNotClaimed();
}
