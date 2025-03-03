package com.example.restful.api.repository;

import com.example.restful.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    List<User> findAllByStatus(int status);
    boolean existsByUsername(String username);
}
