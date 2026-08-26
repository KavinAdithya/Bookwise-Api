package com.techcrack.bookwise.service;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.dtos.passwordReset.ResetTokenDetail;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService extends AbstractLogger<PasswordResetService> {
    private final ConcurrentHashMap<String, ResetTokenDetail> resetTokens;

    public PasswordResetService() {
        super(PasswordResetService.class);
        this.resetTokens = new ConcurrentHashMap<>();
    }

    public String createResetToken(String email) {
        logger.info("For resetting password token generation process started");

        String token = UUID.randomUUID().toString();
        ResetTokenDetail resetTokenDetail = new ResetTokenDetail(
                token,
                ApplicationData.getSystemDate().plusMinutes(
                        ApplicationData.RESET_PASSWORD_TOKEN_EXPIRY
                )
        );

        resetTokens.put(email, resetTokenDetail);

        logger.info("Reset Password Token Generated Successfully");
        return token;
    }

    public boolean verifyResetToken(String email, String token) {
        logger.info("Password Reset Token Verification process started");

        if (!resetTokens.containsKey(email)) {
            return false;
        }

        ResetTokenDetail details = resetTokens.get(email);

        if (details.expiryTime().isBefore(ApplicationData.getSystemDate()) ||
            !details.token().equals(token)) {
            logger.warn("Failed to validate token either invalid token or expired");
            return false;
        }

        // Removing Token
        resetTokens.remove(email);

        logger.info("Token Validated for reset password.");
        return true;
    }
}
