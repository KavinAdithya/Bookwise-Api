package com.techcrack.bookwise.dtos.author.context;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AdminRevenueSummary(
        LocalDateTime startDate,
        LocalDateTime endDate,
        long totalBooksSold,
        long totalBooksBorrowed,
        BigDecimal totalRevenue,
        BigDecimal totalAuthorRevenue,
        BigDecimal totalAuthorRevenueReleased,
        BigDecimal totalAuthorRevenueHold,
        BigDecimal profitOrLossAmount,
        List<IncomeTypeBasedRevenue> incomeTypeBasedRevenues
) {
}
