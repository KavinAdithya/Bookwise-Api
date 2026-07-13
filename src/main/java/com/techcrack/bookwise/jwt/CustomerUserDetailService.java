package com.techcrack.bookwise.jwt;

import com.techcrack.bookwise.entity.UserPrincipal;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserRepository repo;
    private final Logger logger;

    @Autowired
    public CustomerUserDetailService(UserRepository repo) {
        this.repo = repo;
        this.logger = LoggerFactory.getLogger(CustomerUserDetailService.class);
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        logger.info("Spring Security Loading User for {}", username);

        Users user = repo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found"));

        logger.debug("User Loaded Info {}", user);

        logger.info("User Loaded Process done");
        return new UserPrincipal(user);
    }
}
