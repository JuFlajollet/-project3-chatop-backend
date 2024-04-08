package com.chatop.repository;

import com.chatop.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    public Optional<DBUser> findByEmail(String email);
}
