package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.BorrowStatus;
import com.techcrack.bookwise.dtos.borrowbook.layer.DueAmountDetails;
import com.techcrack.bookwise.entity.BorrowBook;
import com.techcrack.bookwise.exceptions.customized.checked.DueAmountFailedException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BorrowBookHelper {

    private final TimeHelper timeHelper;

    public BorrowBookHelper(TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

    public LocalDateTime computeBorrowDueDate(long days) {
        return ApplicationData.getSystemDate().plusDays(days);
    }

    public DueAmountDetails calculateDueAmountFromBorrowBook(SubscriptionService subscriptionService, long currentUserId, BorrowBook entity) throws DueAmountFailedException {

        if (entity.getStatus() != BorrowStatus.BORROWED) {
            throw new DueAmountFailedException("Borrowed Books are eligible to compute due amount. Current Book Status " + entity.getStatus());
        }

        double dailyRent = subscriptionService.getSubscriptionPlanByUserId(currentUserId)
                .getDelayDailyFineAmount();

        return calculateDueAmountDetailsBasedOnDateGap(
                    dailyRent,
                    entity.getQuantity(),
                    entity.getBorrowDate(),
                    entity.getDueDate(),
                    ApplicationData.getSystemDate()
        );
    }

    public DueAmountDetails calculateDueAmountDetailsBasedOnDateGap(double dailyRent,
                                                                    int quantity,
                                                                    LocalDateTime borrowedDate,
                                                                    LocalDateTime dueDate,
                                                                    LocalDateTime currentDate) {
        long totalDays = timeHelper.calculateDaysUpper(borrowedDate, currentDate);

        if (currentDate.isBefore(dueDate)) {
            return new DueAmountDetails(0, totalDays,0,0);
        }

        long daysDelayed = timeHelper.calculateDaysUpper(dueDate, currentDate);

        return calculateDueAmountDetails(dailyRent, totalDays, daysDelayed, quantity);
    }

    // Base Calculation Logic
    private DueAmountDetails calculateDueAmountDetails(double dailyRent, long totalDays, long daysDelayed, long quantity) {
        double dueAmount = dailyRent * daysDelayed * quantity;
        return new DueAmountDetails(daysDelayed, totalDays, dailyRent, dueAmount);
    }
}