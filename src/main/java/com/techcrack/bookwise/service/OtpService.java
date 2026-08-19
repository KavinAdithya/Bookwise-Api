package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.notificationService.NotificationService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.notification.otp.OTPDetail;
import com.techcrack.bookwise.notification.otp.OTPStorage;
import com.techcrack.bookwise.notification.otp.OtpGenerator;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Service;

@Service
public class OtpService extends AbstractLogger<OtpService> {
    private final OtpGenerator otpGenerator;
    private final OTPStorage storage;
    private final NotificationService notificationService;

    public OtpService(OtpGenerator generator, OTPStorage storage, NotificationService notificationService) {
        super(OtpService.class);
        this.otpGenerator = generator;
        this.storage = storage;
        this.notificationService = notificationService;
    }

    public void sendOtp(String email) {
        logger.info("For {} otp send process", email);
        String otp = otpGenerator.generate();

        OTPDetail detail = new OTPDetail(otp, ApplicationData.SYSTEM_DATE
                                                        .plusMinutes(
                                                                ApplicationData.OTP_MINUTE_EXPIRY));
        storage.addOtp(email, detail);

        notificationService
                .sendOtp(
                        email,
                        otp
                );

        logger.info("{} otp send process completed", email);
    }

    public boolean verifyOtp(String email, String otp) {
        OTPDetail detail = storage.getOTP(email);

        if (detail == null || detail.expiryTime().isBefore(ApplicationData.SYSTEM_DATE))  {
            logger.warn("OTP Expired or not generated for this email {}", email);
            return false;
        }

        if (!detail.otp().equals(otp)) {
            logger.warn("OTP doesn't match with generated one");
            return false;
        }

        logger.info("OTP Verified Success fully");
        return true;
    }
}
