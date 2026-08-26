package com.techcrack.bookwise.dtos.passwordReset;

public record PasswordResetRequest(String username, String password, String token) {
}
