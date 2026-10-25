package com.techcrack.bookwise.dtos.admin.context;

import com.techcrack.bookwise.constans.enums.IncomeType;

import java.math.BigDecimal;

public record AdminIncomeTypeBasedRevenue(
        IncomeType incomeType,
        BigDecimal totalRevenue,
        BigDecimal authorRevenue,
        BigDecimal authorRevenueReleased,
        BigDecimal authorRevenueNotHold,
        BigDecimal profitOrLoss
) {
}
