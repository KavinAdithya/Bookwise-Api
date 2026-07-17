package com.techcrack.bookwise.repository;


import com.techcrack.bookwise.entity.PurchaseBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseBookRepository extends JpaRepository<PurchaseBook, Long> {
}
