package com.techcrack.bookwise.applicationServices.borrowbook.dto.layer;

public record DueAmountDetails(long daysDelayed, long totalDays, double daysFineAmount, double totalDueAmount) {
}
