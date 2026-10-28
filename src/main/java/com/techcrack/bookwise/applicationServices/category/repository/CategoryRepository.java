package com.techcrack.bookwise.applicationServices.category.repository;

import com.techcrack.bookwise.applicationServices.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameAndIsActive(String name, boolean isActive);
    boolean existsByNameAndIsActiveTrue(String name);
    List<Category> findAllByIsActiveTrue();
}
