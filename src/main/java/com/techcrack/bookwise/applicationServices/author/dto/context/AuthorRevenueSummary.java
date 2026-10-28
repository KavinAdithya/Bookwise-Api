package com.techcrack.bookwise.applicationServices.author.dto.context;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AuthorRevenueSummary (
    LocalDateTime startDate,
    LocalDateTime endDate,
    long totalBooksSold,
    long totalBooksBorrowed,
    BigDecimal totalAuthorRevenue,
    BigDecimal totalAuthorRevenueReleased,
    BigDecimal totalAuthorRevenueHold,
    List<AuthorIncomeTypeBasedRevenue> incomeTypeBasedRevenues
) {
}
