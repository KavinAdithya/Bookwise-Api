package com.techcrack.bookwise.applicationServices.user.dto.password.request;

import java.time.LocalDateTime;

public record ResetTokenDetail(String token, LocalDateTime expiryTime) {
}
