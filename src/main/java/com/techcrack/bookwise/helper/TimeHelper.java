package com.techcrack.bookwise.helper;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeHelper {
    private static final double ONE_DAY_SECONDS;

    static {
        ONE_DAY_SECONDS = 24L * 60L * 60L;
    }
    public long calculateDays(LocalDateTime startDate,
                              LocalDateTime endDate,
                              boolean isUpperBound) {
        Duration hoursGap = Duration.between(
          startDate,
          endDate
        );

        long totalSecondGap = hoursGap.toSeconds();

        double days =  isUpperBound ? Math.ceil(totalSecondGap / ONE_DAY_SECONDS)  : Math.floor(totalSecondGap / ONE_DAY_SECONDS);

        return (long)days;
    }

    public long calculateDaysUpper(LocalDateTime startDate, LocalDateTime endDate) {
        return calculateDays(startDate, endDate, true);
    }

    public long calculateDaysLower(LocalDateTime startDate, LocalDateTime endDate) {
        return calculateDays(startDate, endDate, false);
    }
}
