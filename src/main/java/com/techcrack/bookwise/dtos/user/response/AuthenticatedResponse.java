package com.techcrack.bookwise.dtos.user.response;

public record AuthenticatedResponse(AuthenticatedUserDetails userDetail, String token) {
}
