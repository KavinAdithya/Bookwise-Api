package com.techcrack.bookwise.dtos.author.context;

import com.techcrack.bookwise.constans.enums.IncomeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IncomeTypeBasedRevenue(
        IncomeType incomeType,
        BigDecimal totalRevenue,
        BigDecimal authorRevenue,
        BigDecimal authorRevenueReleased,
        BigDecimal authorRevenueNotHold,
        BigDecimal profitOrLoss
) {
}
