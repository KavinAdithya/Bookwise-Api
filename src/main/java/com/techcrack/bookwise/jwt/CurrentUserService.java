package com.techcrack.bookwise.jwt;

import com.techcrack.bookwise.entity.UserPrincipal;

public interface CurrentUserService {
    long getCurrentUserId();
    String getCurrentUserName();
    UserPrincipal getCurrentUser();
}
