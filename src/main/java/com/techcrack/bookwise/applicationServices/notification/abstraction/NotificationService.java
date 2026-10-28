package com.techcrack.bookwise.applicationServices.notification.abstraction;

public interface NotificationService {
    boolean sendOtp(String email, String otp);
}
