package com.techcrack.bookwise.applicationServices.user.repository;

import com.techcrack.bookwise.applicationServices.user.dto.context.UserSubscriptionDetail;
import com.techcrack.bookwise.applicationServices.user.dto.response.AdminUserViewResponse;
import com.techcrack.bookwise.applicationServices.user.entity.Users;
import com.techcrack.bookwise.applicationServices.user.repository.queries.projection.UserCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.applicationServices.user.repository.queries.UserJPQLQueries.*;
import static com.techcrack.bookwise.applicationServices.user.repository.queries.UserSQLQueries.USER_COUNT;

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

    @Query(FETCH_ALL_USERS_ACTIVE_BASED)
    List<AdminUserViewResponse> getAllUser(@Param("isActive") boolean isActive);

    @Query(FETCH_USER_SUBSCRIPTION)
    UserSubscriptionDetail getUserSubscription(@Param("userId") long userId);

    @Query(value = USER_COUNT, nativeQuery = true)
    UserCountProjection getUsersCount();
}
