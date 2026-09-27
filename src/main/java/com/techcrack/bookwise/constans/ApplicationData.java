package com.techcrack.bookwise.constans;

import java.time.LocalDateTime;

public class ApplicationData {
    public static int ISBN_LENGTH;
    public static double COMMISSION_PERCENTAGE;
    public static long HARD_CODED_CURRENT_ID;
    public static long OTP_MINUTE_EXPIRY;
    public static long RESET_PASSWORD_TOKEN_EXPIRY;
    public static long LOGIN_EXPIRY_SECONDS;

    static {
        ISBN_LENGTH = 13;
        COMMISSION_PERCENTAGE = 20.0;
        HARD_CODED_CURRENT_ID = 1;
        OTP_MINUTE_EXPIRY = 5;
        RESET_PASSWORD_TOKEN_EXPIRY = 5;
        LOGIN_EXPIRY_SECONDS = 1000 * 60 * 20;
    }

    public static LocalDateTime getSystemDate() {
        return LocalDateTime.now();
    }
}
