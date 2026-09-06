package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.enums.Roles;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Users user;

    public UserPrincipal(Users user) {
        this.user = user;
    }

    public long getUserId() {
        return user.getId();
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("USER"));

        if (user.getRole() == Roles.AUTHOR) {
            authorities.add(new SimpleGrantedAuthority("AUTHOR"));
            return authorities;
        }

        if (user.getRole() == Roles.USER)
            return authorities;

        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    @NonNull
    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public String getRole() {
        return user.getRole().toString().toUpperCase();
    }
}
