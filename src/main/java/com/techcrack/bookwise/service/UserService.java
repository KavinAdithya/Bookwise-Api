package com.techcrack.bookwise.service;

import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.InvalidDataException;
import com.techcrack.bookwise.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.utils.exceptions.templates.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final Logger logger;

    public UserService(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.logger = LoggerFactory.getLogger(UserService.class);
    }

    public Users register(Users user) {
        logger.info("User registration started with {}", user);

        Errors errors = validateData(user);

        if (errors.hasErrors()) {
            logger.error("Errors : {}", errors.getData());
            throw new InvalidDataException("User Data is invalid : " + errors.getData());
        }

        user.initialize();
        user.setPassword(encoder.encode(user.getPassword()));

        logger.info("User data is validated successfully.");
        return repo.save(user);
    }

    private Errors validateData(Users user) {
        Errors errors = new Errors();

        if (!isValidPassWord(user.getPassword())) {
            errors.addErrorMessage("Invalid Password : Ensure Password length is min 8 and contains alphanumeric and special letters");
        }

        return errors;
    }

    private boolean isValidPassWord(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean isDigit = false, isAlpha = false, isSpl = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                isDigit = true;
            } else if (Character.isAlphabetic(ch))  {
                isAlpha = true;
            } else {
                isSpl = true;
            }

            if (isDigit && isAlpha && isSpl) {
                return true;
            }
        }

        return false;
    }

    public String authenticate(String username, String password) {
        logger.info("Authentication started for {} and {}", username, password);

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        logger.info("Authentication Details are valid");

        return jwtService.generateToken(username);
    }
}
