package com.techcrack.bookwise.applicationServices.user.dto.password.request;

public record PasswordResetRequest(String username, String password, String token) {
}
