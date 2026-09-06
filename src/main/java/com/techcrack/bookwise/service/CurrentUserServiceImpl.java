package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.entity.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public long getCurrentUserId() {
       UserPrincipal principal = getCurrentUser();
        return principal.getUserId();
    }

    @Override
    public String getCurrentUserName() {
        UserPrincipal principal = getCurrentUser();
        return principal.getUsername();
    }

    @Override
    public UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        assert authentication != null;
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication.getPrincipal();

        assert authenticationToken != null;

        return (UserPrincipal) authenticationToken.getPrincipal();
    }
}
