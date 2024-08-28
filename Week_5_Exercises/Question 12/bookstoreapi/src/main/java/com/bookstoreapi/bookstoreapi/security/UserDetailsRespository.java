package com.bookstoreapi.bookstoreapi.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRespository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByUsername(String username);
}
