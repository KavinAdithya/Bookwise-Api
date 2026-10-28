package com.techcrack.bookwise.applicationServices.user.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerator {
    private final SecureRandom random;

    public OtpGenerator() {
        this.random = new SecureRandom();
    }

    public String generate() {
        return String.format("%06d", random.nextInt(1_000_000));
    }
}
