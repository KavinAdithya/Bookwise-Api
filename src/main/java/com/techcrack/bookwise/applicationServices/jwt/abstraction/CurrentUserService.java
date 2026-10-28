package com.techcrack.bookwise.applicationServices.jwt.abstraction;

import com.techcrack.bookwise.applicationServices.jwt.entity.UserPrincipal;


public interface CurrentUserService {
    long getCurrentUserId();
    String getCurrentUserName();
    UserPrincipal getCurrentUser();
}
