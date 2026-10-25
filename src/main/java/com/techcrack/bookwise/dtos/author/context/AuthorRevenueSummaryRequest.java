package com.techcrack.bookwise.dtos.author.context;

import com.techcrack.bookwise.constans.enums.IncomeType;

import java.time.LocalDateTime;

public record AuthorRevenueSummaryRequest(LocalDateTime startDate, LocalDateTime endDate, IncomeType incomeType) {
}
