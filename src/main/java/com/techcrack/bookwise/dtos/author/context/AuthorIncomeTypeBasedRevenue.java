package com.techcrack.bookwise.dtos.author.context;

import com.techcrack.bookwise.constans.enums.IncomeType;

import java.math.BigDecimal;

public record AuthorIncomeTypeBasedRevenue(
        IncomeType incomeType,
        BigDecimal authorRevenue,
        BigDecimal authorRevenueReleased,
        BigDecimal authorRevenueNotHold
) {
}