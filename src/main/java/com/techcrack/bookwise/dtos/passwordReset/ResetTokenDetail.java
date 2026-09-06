package com.techcrack.bookwise.dtos.passwordReset;

import java.time.LocalDateTime;

public record ResetTokenDetail(String token, LocalDateTime expiryTime) {
}
