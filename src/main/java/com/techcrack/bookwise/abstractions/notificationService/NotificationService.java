package com.techcrack.bookwise.abstractions.notificationService;

public interface NotificationService {
    boolean sendOtp(String email, String otp);
}
