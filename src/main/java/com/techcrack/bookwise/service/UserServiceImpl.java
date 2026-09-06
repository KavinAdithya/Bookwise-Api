package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.dtos.passwordReset.PasswordResetRequest;
import com.techcrack.bookwise.dtos.passwordReset.SendOtpRequest;
import com.techcrack.bookwise.dtos.passwordReset.VerifyOtpRequest;
import com.techcrack.bookwise.dtos.user.context.AuthenticationResult;
import com.techcrack.bookwise.entity.UserPrincipal;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractService;
import com.techcrack.bookwise.validations.UserServiceValidation;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends AbstractService<UserServiceImpl, UserRepository, UserServiceValidation>
                            implements UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final PasswordResetService passwordResetService;

    public UserServiceImpl(UserRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtService jwtService,
                           UserServiceValidation validation,
                           CurrentUserService userSession,
                           OtpService otpService,
                           PasswordResetService passwordResetService) {
        super(UserServiceImpl.class, repo, validation, userSession);
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.otpService = otpService;
        this.passwordResetService = passwordResetService;
    }

    public Users register(Users user) {
        logger.info("User registration started with {}", user.getUsername());

        Errors errors = validations.validateUserData(user);

        if (errors.hasErrors()) {
            logger.error("Errors : {}", errors.getData());
            throw new InvalidDataException("User Data is invalid : " + errors.getData());
        }

        user.initialize(null);
        user.setPassword(encoder.encode(user.getPassword()));

        logger.info("User data is validated successfully.");
        return repo.save(user);
    }

    @Override
    public void remove(long key) {
        repo.deleteById(key);
    }

    @Override
    public Users update(Users entity) {
        return repo.save(entity);
    }

    @Override
    public Users get(long key) {
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(Users.class, "User Not Found"));
    }


    public AuthenticationResult authenticate(String username, String password) {

        logger.info("Authentication Process started for {}", username);

        logger.debug("Authentication  Data  {} and {}", username, password);

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        logger.info("Authentication Details are valid");

        String token = jwtService.generateToken(username);

        return new AuthenticationResult(
                token,
                (UserPrincipal) authentication.getPrincipal()
        );
    }

    public Users getUser(String username) {
        return repo.findUserByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException(Users.class, "User doesn't exists for the username : " + username));
    }

    public String verifyOtp(VerifyOtpRequest request) {
        logger.info("OTP verification started for user {}", request.username());

        String email = getUser(request.username()).getEmail();
        boolean isVerified = otpService.verifyOtp(email, request.otp());

        if (!isVerified) {
            throw new InvalidDataException("OTP Expired or Invalid");
        }

        String resetToken = passwordResetService.createResetToken(email);

        logger.info("Otp Verified for User {} and generated token {}", request.username(), resetToken);

        return resetToken;
    }

    @Override
    public void sendOtp(SendOtpRequest request) {
        logger.info("Otp Sending process started for user {}", request.username());

        String email = getUser(request.username())
                .getEmail();

        logger.info("Otp Email found with user {}, Email {}", request.username(), email);

        otpService.sendOtp(email);

        logger.info("OTP Send Process Completed Successfully");
    }

    @Transactional
    @Override
    public void resetPassword(PasswordResetRequest request) {
        logger.info("Password Reset Process Started for {}", request.username());

        Users users = getUser(request.username());

        boolean isValidToken = passwordResetService.verifyResetToken(users.getEmail(), request.token());

        if (!isValidToken) {
            logger.info("Invalid Reset Token Or Token Expired");
            throw new InvalidDataException("Invalid Reset Token");
        }

        boolean isValidPassword = validations.isValidPassWord(request.password());
        if (! isValidPassword) {
            logger.info("Password is too weak. So Password Reset Failed");
            throw new InvalidDataException("Password is Weak. Invalid Password : Ensure Password length is min 8 and contains alphanumeric and special letters.");
        }

        users.setPassword(encoder.encode(request.password()));

        logger.info("Password Reset Process Completed");
    }
}
