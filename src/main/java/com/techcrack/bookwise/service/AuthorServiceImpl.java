package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.repository.AuthorRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl extends AbstractRepository<AuthorServiceImpl, AuthorRepository>
                            implements AuthorService {

    private final SubscriptionService subscriptionService;

    public AuthorServiceImpl(AuthorRepository repo, SubscriptionService subscriptionService, CurrentUserService userSession) {
        super(AuthorServiceImpl.class, repo, userSession);
        this.subscriptionService = subscriptionService;
    }

    public Author register(Author author) {
        logger.info("Registration process for author has started {}", author.getUser().getUsername());

        author = repo.save(author);

        logger.debug("Registered Author Info : {}", author);

        logger.info("Registration process for author has completed {} and moved for admin verification", author.getUser().getUsername());
        return author;
    }

    @Override
    public void remove(long key) {
        repo.deleteById(key);
    }

    @Override
    public Author update(Author entity) {
       return register(entity);
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

        // For new Authors enabling one-month premium subscription free
        for (long authorId : authorIds) {
            Subscription subscription = subscriptionService.subscriptionPremiumPlanForOneMonth(authorId, new DiscountDetails(100));
            logger.debug("Subscription info {}", subscription);
        }

        logger.info("One Month Free Premium Subscription activated successfully");
        return rowsAffected;
    }

    public int rejectAuthors(List<Long> authorIds) {
        logger.info("Rejecting Author status to approve process started");

        int rowsAffected = repo.updateAuthorStatusByIds(Status.APPROVED, authorIds);

        logger.debug("Total Authors Rejected {} Affected rows {}", authorIds, rowsAffected);
        logger.info("Author Rejected process done for author ids {}", authorIds);

        return rowsAffected;
    }
    public Author getAuthorByUserId(long key) {
        return repo.findByUser_Id(key)
                .orElseThrow(() -> new ObjectNotFoundException(Author.class, "Author not found with user id : " + key));
    }
}
