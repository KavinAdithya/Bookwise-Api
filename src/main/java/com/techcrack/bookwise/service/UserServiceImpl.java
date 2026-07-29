package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.utils.AbstractRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends AbstractRepository<UserServiceImpl, UserRepository>
                            implements UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager, JwtService jwtService) {
        super(UserServiceImpl.class, repo);
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public Users register(Users user) {
        logger.info("User registration started with {}", user.getUsername());

        Errors errors = validateData(user);

        if (errors.hasErrors()) {
            logger.error("Errors : {}", errors.getData());
            throw new InvalidDataException("User Data is invalid : " + errors.getData());
        }

        user.initialize(ApplicationData.HARD_CODED_CURRENT_ID);
        user.setPassword(encoder.encode(user.getPassword()));

        logger.info("User data is validated successfully.");
        return repo.save(user);
    }

    @Override
    public void remove(long key) {

    }

    @Override
    public Users update(Users entity) {
        return null;
    }

    @Override
    public Users get(long key) {
        return repo.findById(key)
                .orElseThrow(() -> new ObjectNotFoundException(Users.class, "User Not Found"));
    }

    private Errors validateData(Users user) {
        logger.debug("Validating User data process started");
        Errors errors = new Errors();

        if (!isValidPassWord(user.getPassword())) {
            errors.addErrorMessage("Invalid Password : Ensure Password length is min 8 and contains alphanumeric and special letters");
        }

        logger.debug("Validating user data is completed");
        return errors;
    }

    private boolean isValidPassWord(String password) {
        logger.debug("Password Validating Process Started for {}", password);

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
                logger.debug("Password is Validated successfully.");
                return true;
            }
        }

        logger.debug("Password Validation Failed Due to IsDigit = {}, IsAlpha = {}, IsSpecialCharacter = {}", isDigit, isAlpha, isSpl);

        return false;
    }

    public String authenticate(String username, String password) {

        logger.info("Authentication Process started for {}", username);

        logger.debug("Authentication  Data  {} and {}", username, password);

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        logger.info("Authentication Details are valid");

        return jwtService.generateToken(username);
    }
}
