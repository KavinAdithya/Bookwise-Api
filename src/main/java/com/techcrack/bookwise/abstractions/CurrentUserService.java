package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.UserPrincipal;


public interface CurrentUserService {
    long getCurrentUserId();
    String getCurrentUserName();
    UserPrincipal getCurrentUser();
}
