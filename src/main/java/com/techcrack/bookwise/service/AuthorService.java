package com.techcrack.bookwise.service;

import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.constans.Subscriptions;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repo;
    private final SubscriptionService subscriptionService;
    private final Logger logger;

    public AuthorService(AuthorRepository repo, SubscriptionService subscriptionService) {
        this.repo = repo;
        this.subscriptionService = subscriptionService;
        this.logger = LoggerFactory.getLogger(AuthorService.class);
    }

    public Author register(Author author) {
        logger.info("Registration process for author has started {}", author.getUser().getUsername());

        author = repo.save(author);

        logger.debug("Registered Author Info : {}", author);

        logger.info("Registration process for author has completed {} and moved for admin verification", author.getUser().getUsername());
        return author;
    }

    public Author get(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Author.class, "Author not found. with " + id));
    }

    public boolean isAuthorValid(Author author) {
        return author != null && author.getUser() != null &&
                author.getUser().isActive() && author.getStatus()== Status.APPROVED;
    }

    public List<Author> getPendingAuthors() {
        return repo.findAllByStatusAndIsActiveTrue(Status.PENDING);
    }

    public int approveAuthors(List<Long> authorIds) {
        logger.info("Updating Author status to approve process started");

        int rowsAffected = repo.updateAuthorStatusByIds(Status.APPROVED, authorIds);

        logger.debug("Total Authors {} Affected rows {}", authorIds, rowsAffected);
        logger.info("Author Approval process done for author ids {}", authorIds);

        for (long authorId : authorIds) {
            Subscription subscription = subscriptionService.subscriptionPremiumForOneMonth(authorId, ApplicationData.SYSTEM_DATE);
            logger.debug("Subscription info {}", subscription);
        }

        logger.info("One Month Free Premium Subscription activated successfully");
        return rowsAffected;
    }
}
