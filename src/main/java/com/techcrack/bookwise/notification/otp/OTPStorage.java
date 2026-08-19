package com.techcrack.bookwise.notification.otp;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OTPStorage {
    private final Map<String, OTPDetail> otpDetailsMap;

    public OTPStorage() {
        this.otpDetailsMap = new HashMap<>();
    }

    public OTPDetail getOTP(String email) {
        if (otpDetailsMap.containsKey(email)) {
            return otpDetailsMap.get(email);
        }

        return null;
    }

    public void addOtp(String email, OTPDetail detail) {
        otpDetailsMap.put(email, detail);
    }

}
