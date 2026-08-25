package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.UserService;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.jwt.JwtService;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.utils.AbstractService;
import com.techcrack.bookwise.validations.UserServiceValidation;
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

    public UserServiceImpl(UserRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtService jwtService,
                           UserServiceValidation validation,
                           CurrentUserService userSession) {
        super(UserServiceImpl.class, repo, validation, userSession);
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
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
