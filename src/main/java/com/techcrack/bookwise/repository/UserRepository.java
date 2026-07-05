package com.techcrack.bookwise.repository;

import com.techcrack.bookwise.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUsername(String username);
}
