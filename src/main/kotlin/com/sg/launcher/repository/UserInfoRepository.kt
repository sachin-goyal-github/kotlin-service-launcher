package com.sg.launcher.repository

import com.sg.launcher.repository.entity.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository : JpaRepository<UserInfoEntity?, Long?> {
    fun findByEmail(email: String?): UserInfoEntity?
}