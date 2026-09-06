package com.techcrack.bookwise.dtos.user.response;

public record AuthenticatedResponse(String name, String role, String token) {
}
