package com.techcrack.bookwise.applicationServices.user.dto.context;

import com.techcrack.bookwise.applicationServices.jwt.entity.UserPrincipal;

public record AuthenticationResult(String token, UserPrincipal userPrincipal) {
}
