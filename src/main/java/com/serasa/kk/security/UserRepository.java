package com.serasa.kk.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SerasaUser, Long> {
    Optional<SerasaUser> findByUsername(String username);
}
