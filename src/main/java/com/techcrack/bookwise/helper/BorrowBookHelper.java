package com.techcrack.bookwise.helper;

import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.ApplicationData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BorrowBookHelper {

    public LocalDateTime computeBorrowDueDate(long days) {
        return ApplicationData.getSystemDate().plusDays(days);
    }
}
