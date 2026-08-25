package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.entity.BorrowBook;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class AuthorRevenueHelper {
    public double calculateBorrowBookRevenue(BorrowBook borrowBook) {
        if (borrowBook == null || borrowBook.getBook() == null) {
            return 0.0;
        }

        double monthlyRent = borrowBook.getBook().getBorrowFee();
        LocalDateTime startDate = borrowBook.getBorrowDate();
        LocalDateTime returnDate = borrowBook.getReturnDate();

        if (monthlyRent <= 0 || startDate == null || returnDate == null || startDate.isAfter(returnDate)) {
            return 0.0;
        }

        long daysUsed = ChronoUnit.DAYS.between(startDate, returnDate);

        return daysUsed * (monthlyRent / 30) * borrowBook.getQuantity();
    }
}
