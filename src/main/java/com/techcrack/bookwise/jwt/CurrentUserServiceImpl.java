package com.techcrack.bookwise.jwt;

import com.techcrack.bookwise.entity.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserServiceImpl implements CurrentUserService{
    @Override
    public long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder
//                                                .getContext()
//                                                .getAuthentication();
//        assert authentication != null;
//        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//
//        assert principal != null;
//        return principal.getUserId();
        return 1;
    }

    @Override
    public String getCurrentUserName() {
//        Authentication authentication = SecurityContextHolder
//                .getContext()
//                .getAuthentication();
//        assert authentication != null;
//        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//
//        assert principal != null;
//        return principal.getUsername();
        return "alicejohnson";
    }

    @Override
    public UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        assert authentication != null;
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        assert principal != null;
        return principal;
    }
}
