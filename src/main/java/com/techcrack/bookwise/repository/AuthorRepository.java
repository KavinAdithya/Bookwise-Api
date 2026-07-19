package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.constans.JPQLQueries;
import com.techcrack.bookwise.constans.Status;
import com.techcrack.bookwise.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.techcrack.bookwise.constans.JPQLQueries.UPDATE_AUTHOR_STATUS;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByStatusAndIsActiveTrue(Status status);

    @Transactional
    @Modifying
    @Query(value = UPDATE_AUTHOR_STATUS, nativeQuery = false)
    int updateAuthorStatusByIds(@Param("status") Status status, @Param("ids") List<Long> authorIds);
}
