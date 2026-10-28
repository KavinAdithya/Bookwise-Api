package com.techcrack.bookwise.applicationServices.admin.dto.response;

import java.math.BigDecimal;

public record AdminHomePageSummaryResponse(
        long totalUsers,
        long totalBooks,
        long totalApprovedBooks,
        long totalPendingBooks,
        long totalRejectedBooks,
        long totalAuthors,
        BigDecimal totalRevenue
) {
}
