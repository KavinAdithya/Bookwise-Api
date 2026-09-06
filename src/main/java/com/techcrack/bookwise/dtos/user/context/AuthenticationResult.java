package com.techcrack.bookwise.dtos.user.context;

import com.techcrack.bookwise.entity.UserPrincipal;

public record AuthenticationResult(String token, UserPrincipal userPrincipal) {
}
