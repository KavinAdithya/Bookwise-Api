package com.techcrack.bookwise.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService service;
    private final ApplicationContext context;

    public JwtFilter(JwtService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterProcess(request, response);

        filterChain.doFilter(request, response);
    }

    private void filterProcess(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return;

        String token = authHeader.substring(7);
        String username = service.extractUserName(token);

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) return;

        UserDetails userDetails = context.getBean(CustomerUserDetailService.class)
                                          .loadUserByUsername(username);

        if (!service.validateToken(token, userDetails)) return;

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
        authToken
                .setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request)
                );

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
