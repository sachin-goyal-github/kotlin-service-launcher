package com.sg.launcher.repository;

import com.sg.launcher.repository.entity.UserCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, Long> {
    UserCredentialsEntity findByUserId(Long userId );
}