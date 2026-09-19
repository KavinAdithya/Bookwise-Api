package com.techcrack.bookwise.dtos.user.response;

import com.techcrack.bookwise.constans.enums.Roles;

public record AdminUserViewResponse(long id, String name, String username, String email, Roles role) {
}
