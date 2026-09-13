package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.AuthorService;
import com.techcrack.bookwise.abstractions.SubscriptionService;
import com.techcrack.bookwise.constans.ApplicationData;
import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse;
import com.techcrack.bookwise.dtos.subscription.DiscountDetails;
import com.techcrack.bookwise.entity.Author;
import com.techcrack.bookwise.entity.Subscription;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.repository.AuthorRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
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

    public List<AdminViewAuthorResponse> getAuthorsBasedOnStatus(Status status) {
        return repo.findAuthorsByStatus(status);
    }

    @Transactional
    public int approveAuthors(List<Long> authorIds) {
        logger.info("Updating Author status to approve process started");

        int rowsAffected = repo.updateAuthorStatusByIds(Status.APPROVED, authorIds, userSession.getCurrentUserId(), ApplicationData.getSystemDate());

        logger.debug("Total Authors {} Affected rows {}", authorIds, rowsAffected);
        logger.info("Author Approval process done for author ids {}", authorIds);

            List<Long> userIds = repo.fetchAllUserIds(authorIds);

        // For new Authors enabling one-month premium subscription free
        for (long userId : userIds) {
            Subscription subscription = subscriptionService.subscriptionPremiumPlanForOneMonth(userId, new DiscountDetails(100));
            logger.debug("Subscription info {}", subscription);
        }

        logger.info("One Month Free Premium Subscription activated successfully");
        return rowsAffected;
    }

    @Transactional
    public int rejectAuthors(List<Long> authorIds) {
        logger.info("Rejecting Author status to approve process started");

        int rowsAffected = repo.updateAuthorStatusByIds(Status.REJECTED, authorIds, userSession.getCurrentUserId(), ApplicationData.getSystemDate());

        logger.debug("Total Authors Rejected {} Affected rows {}", authorIds, rowsAffected);
        logger.info("Author Rejected process done for author ids {}", authorIds);

        return rowsAffected;
    }
    public Author getAuthorByUserId(long key) {
        return repo.findByUser_Id(key)
                .orElseThrow(() -> new ObjectNotFoundException(Author.class, "Author not found with user id : " + key));
    }

    @Override
    public List<Author> getAllActiveAuthors() {
        return repo.findByIsActiveTrue();
    }

    @Override
    public List<AdminViewAuthorResponse> getAllActiveAuthorForAdminView() {
        return repo.getAllAuthorAdminView();
    }

    public Author getAuthorById(long id) {
        return repo.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ObjectNotFoundException(Author.class, "Author not found with id " + id));
    }

    @Override
    public long getAuthorIdByUserId(long userId) {
        return repo.findAuthorIdByUserId(userId);
    }
}
