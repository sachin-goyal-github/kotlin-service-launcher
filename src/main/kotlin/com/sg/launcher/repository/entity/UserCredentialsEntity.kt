package com.sg.launcher.repository.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_credentials")
class UserCredentialsEntity {
    @get:Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @get:Column(name = "user_id")
    var userId: Long? = null

    @get:Column(name = "user_name")
    var userName: String? = null

    @get:Column(name = "salt")
    var salt: ByteArray? = null

    @get:Column(name = "password_hash")
    var passwordHash: ByteArray? = null

    constructor()
    constructor(userId: Long?, userName: String?, salt: ByteArray?, passwordHash: ByteArray?) {
        this.userId = userId
        this.userName = userName
        this.salt = salt
        this.passwordHash = passwordHash
    }
}