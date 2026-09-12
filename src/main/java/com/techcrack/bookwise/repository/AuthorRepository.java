package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.enums.Status;
import com.techcrack.bookwise.dtos.author.response.AdminViewAuthorResponse;
import com.techcrack.bookwise.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

import static com.techcrack.bookwise.constans.queries.JPQLQueries.*;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByStatusAndIsActiveTrue(Status status);

    @Transactional
    @Modifying
    @Query(value = UPDATE_AUTHOR_STATUS, nativeQuery = false)
    int updateAuthorStatusByIds(@Param("status") Status status, @Param("ids") List<Long> authorIds);


    Optional<Author> findByUser_Id(Long userId);

    List<Author> findByIsActiveTrue();

    @Query(FETCH_AUTHORS_ADMIN_VIEW)
    List<AdminViewAuthorResponse> getAllAuthorAdminView();

    Optional<Author> findByIdAndIsActiveTrue(long id);

    @Query(FETCH_ALL_USER_IDS)
    List<Long> fetchAllUserIds(@Param("authorIds") List<Long> authorIds);
}
