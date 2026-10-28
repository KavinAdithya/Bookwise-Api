package com.techcrack.bookwise.applicationServices.admin.dto.context;

import java.math.BigDecimal;
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
        List<AdminIncomeTypeBasedRevenue> incomeTypeBasedRevenues
) {
}
