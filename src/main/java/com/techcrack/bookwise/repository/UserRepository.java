package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.dtos.book.response.UserBookDetailViewResponse;
import com.techcrack.bookwise.dtos.book.response.UserBookViewResponse;
import com.techcrack.bookwise.dtos.user.context.UserSubscriptionDetail;
import com.techcrack.bookwise.dtos.user.response.AdminUserViewResponse;
import com.techcrack.bookwise.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.*;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String password);
    boolean existsByContact(String contact);

    @Query(FIND_EXISTING_USER)
    List<Users> findExistingUsers(
        @Param("username") String username,
        @Param("email")    String email,
        @Param("contact") String contact
    );

    @Query(FETCH_ALL_USERS)
    List<AdminUserViewResponse> getAllUser();

    @Query(FETCH_ALL_USERS_IsActive_BASED)
    List<AdminUserViewResponse> getAllUser(@Param("isActive") boolean isActive);

    @Query(FETCH_USER_SUBSCRIPTION)
    UserSubscriptionDetail getUserSubscription(@Param("userId") long userId);

}
