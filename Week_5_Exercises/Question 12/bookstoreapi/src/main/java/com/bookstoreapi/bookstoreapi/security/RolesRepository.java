package com.bookstoreapi.bookstoreapi.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
