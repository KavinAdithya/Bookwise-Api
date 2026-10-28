package com.techcrack.bookwise.applicationServices.user.dto.password.context;

import java.time.LocalDateTime;

public record OTPDetail(String otp, LocalDateTime expiryTime) {
}
