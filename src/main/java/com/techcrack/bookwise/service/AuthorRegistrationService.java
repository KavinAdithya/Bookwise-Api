package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.responseHelper.RegistrationResult;
import com.techcrack.bookwise.utils.BaseLoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorRegistrationService extends BaseLoggerService<AuthorRegistrationService, AuthorService> {
    private final UserRegistrationService userRegistrationService;

    public AuthorRegistrationService(AuthorService service, UserRegistrationService userRegistrationService) {
        super(AuthorRegistrationService.class, service);
        this.userRegistrationService = userRegistrationService;
    }

    public RegistrationResult<Author, Subscription> register(Author author, Subscription subscription) {
        logger.info("Author Registration process started {}", author.getUser().getUsername());

        RegistrationResult<Users, Subscription> userRegistrationResult = userRegistrationService.register(author.getUser(), subscription);
        author.setUser(userRegistrationResult.entity());

        logger.debug("User & Subscription Registered Info : {}", userRegistrationResult);

        author = service.register(author);

        logger.debug("Saved Author Info : {}" , author);
        logger.info("Author Registration Process done for {}", author.getUser().getUsername());

        return new RegistrationResult<>(author, userRegistrationResult.relatedEntity());
    }
}
