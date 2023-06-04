package com.sg.launcher.repository;

import com.sg.launcher.repository.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
    Set<UserTokenEntity> findByUserId(Long userId );

    UserTokenEntity findByUserToken(String userToken );
}