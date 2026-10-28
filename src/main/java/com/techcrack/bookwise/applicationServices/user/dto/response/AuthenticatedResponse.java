package com.techcrack.bookwise.applicationServices.user.dto.response;

public record AuthenticatedResponse(AuthenticatedUserDetails userDetail, String token) {
}
