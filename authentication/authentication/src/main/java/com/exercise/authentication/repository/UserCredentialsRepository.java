package com.exercise.authentication.repository;

import com.exercise.authentication.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByUsername(String username);

    UserCredentials findByUsernameIgnoreCase(String username);
}
