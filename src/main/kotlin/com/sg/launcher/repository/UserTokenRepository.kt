package com.sg.launcher.repository

import com.sg.launcher.repository.entity.UserTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserTokenRepository : JpaRepository<UserTokenEntity?, Long?> {
    fun findByUserId(userId: Long?): Set<UserTokenEntity?>?
    fun findByUserToken(userToken: String?): UserTokenEntity?
}