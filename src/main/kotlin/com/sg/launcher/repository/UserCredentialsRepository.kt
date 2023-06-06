package com.sg.launcher.repository

import com.sg.launcher.repository.entity.UserCredentialsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserCredentialsRepository : JpaRepository<UserCredentialsEntity?, Long?> {
    fun findByUserId(userId: Long?): UserCredentialsEntity?
}