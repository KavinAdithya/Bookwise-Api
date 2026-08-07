package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.jwt.CurrentUserService;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Service;

@Service
public class AuthorRegistrationService extends AbstractLogger<AuthorRegistrationService> {
    private final AuthorService authorService;
    private final UserRegistrationService userRegistrationService;
    private final CurrentUserService userSession;

    public AuthorRegistrationService(AuthorService authorService, UserRegistrationService userRegistrationService, CurrentUserService userSession) {
        super(AuthorRegistrationService.class);
        this.authorService = authorService;
        this.userSession = userSession;
        this.userRegistrationService = userRegistrationService;
    }

    public RegistrationResult<Author, Subscription> register(Author author, Subscription subscription) {
        logger.info("Author Registration process started {}", author.getUser().getUsername());

        RegistrationResult<Users, Subscription> userRegistrationResult = userRegistrationService.register(author.getUser(), subscription);
        author.setUser(userRegistrationResult.entity());

        logger.debug("User & Subscription Registered Info : {}", userRegistrationResult);

        author = authorService.register(author);

        logger.debug("Saved Author Info : {}" , author);
        logger.info("Author Registration Process done for {}", author.getUser().getUsername());

        return new RegistrationResult<>(author, userRegistrationResult.relatedEntity());
    }
}
