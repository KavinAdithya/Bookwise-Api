package com.techcrack.bookwise.dtos.user.response;

import java.util.List;

public record AuthenticatedResponse(String username, String role, String token) {
}
