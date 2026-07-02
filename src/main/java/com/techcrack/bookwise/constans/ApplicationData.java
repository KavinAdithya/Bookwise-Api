package com.techcrack.bookwise.constans;

import java.time.LocalDateTime;

public class ApplicationData {
    public static LocalDateTime SYSTEM_DATE;

    static {
        SYSTEM_DATE = LocalDateTime.now();
    }
}
