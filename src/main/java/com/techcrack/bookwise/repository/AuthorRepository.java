package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse;
import com.techcrack.bookwise.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.*;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(FETCH_AUTHOR_ADMIN_VIEW_PENDING)
    List<AdminViewAuthorResponse> findAuthorsByStatus(Status status);

    @Transactional
    @Modifying
    @Query(value = UPDATE_AUTHOR_STATUS, nativeQuery = false)
    int updateAuthorStatusByIds(@Param("status") Status status,
                                @Param("ids") List<Long> authorIds,
                                @Param("updatedBy") Long updatedBy,
                                @Param("updatedAt")LocalDateTime updatedAt);


    Optional<Author> findByUser_Id(Long userId);

    List<Author> findByIsActiveTrue();

    @Query(FETCH_AUTHORS_ADMIN_VIEW)
    List<AdminViewAuthorResponse> getAllAuthorsAdminView();

    Optional<Author> findByIdAndIsActiveTrue(long id);

    @Query(FETCH_ALL_USER_IDS)
    List<Long> fetchAllUserIds(@Param("authorIds") List<Long> authorIds);

    @Query(FIND_AUTHOR_ID_BY_USER_ID)
    Long findAuthorIdByUserId(long userId);
}
