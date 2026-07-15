package com.techcrack.bookwise.constans;

import java.time.LocalDateTime;

public class ApplicationData {
    public static LocalDateTime SYSTEM_DATE;
    public static int ISBN_LENGTH;
    public static double COMMISSION_PERCENTAGE;

    static {
        SYSTEM_DATE = LocalDateTime.now();
        ISBN_LENGTH = 13;
        COMMISSION_PERCENTAGE = 20.0;
    }
}
