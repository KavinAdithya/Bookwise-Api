package com.techcrack.bookwise.notification;

import com.techcrack.bookwise.abstractions.notificationService.NotificationService;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailNotificationService extends AbstractLogger<EmailNotificationService>
        implements NotificationService {
    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        super(EmailNotificationService.class);
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendOtp(String email, String otp) {
        logger.info("For Sending OTP Process Started");
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("Book Wise - OTP Verification");
            message.setText(
                    "Your BookWise verification OTP is: " + otp +
                            "\n\nThis OTP is valid for 5 minutes."
            );

            mailSender.send(message);
        }
        catch (Exception e) {
            logger.info("Failed to send email notification due to {}", e.getMessage());
            throw e;
        }

        logger.info("OTP send successfully via Email");

        return true;
    }
}
