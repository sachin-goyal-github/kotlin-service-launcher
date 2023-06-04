package com.sg.launcher.repository;

import com.sg.launcher.repository.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    UserInfoEntity findByEmail(String email );
}