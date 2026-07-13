package com.techcrack.bookwise.service;

import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repo;
    private final Logger logger;

    public AuthorService(AuthorRepository repo) {
        this.repo = repo;
        this.logger = LoggerFactory.getLogger(AuthorService.class);
    }

    public Author register(Author author) {
        logger.info("Registration process for author has started {}", author.getUser().getUsername());

        author = repo.save(author);

        logger.debug("Registered Author Info : {}", author);

        logger.info("Registration process for author has completed {} and moved for admin verification", author.getUser().getUsername());
        return author;
    }
}
