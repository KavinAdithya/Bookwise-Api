package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
