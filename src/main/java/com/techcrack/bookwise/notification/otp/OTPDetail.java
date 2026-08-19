package com.techcrack.bookwise.notification.otp;

import java.time.LocalDateTime;

public record OTPDetail(String otp, LocalDateTime expiryTime) {
}
