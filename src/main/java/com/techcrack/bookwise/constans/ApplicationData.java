package com.techcrack.bookwise.constans;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ApplicationData {
    public static LocalDateTime SYSTEM_DATE;
    public static int ISBN_LENGTH;
    public static double COMMISSION_PERCENTAGE;
    public static long HARD_CODED_CURRENT_ID;
    public static long OTP_MINUTE_EXPIRY;

    static {
        SYSTEM_DATE = LocalDateTime.now();
        ISBN_LENGTH = 13;
        COMMISSION_PERCENTAGE = 20.0;
        HARD_CODED_CURRENT_ID = 1;
        OTP_MINUTE_EXPIRY = 5;
    }
}
