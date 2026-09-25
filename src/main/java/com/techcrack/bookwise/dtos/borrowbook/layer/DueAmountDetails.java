package com.techcrack.bookwise.dtos.borrowbook.layer;

public record DueAmountDetails(long daysDelayed, long totalDays, double daysFineAmount, double totalDueAmount) {
}
