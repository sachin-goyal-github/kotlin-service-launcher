package com.sg.launcher.repository.entity

import javax.persistence.*

@Entity
@Table(name = "user_info")
class UserInfoEntity {
    @get:Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @get:Column(name = "email")
    var email: String? = null

    @get:Column(name = "full_name")
    var fullName: String? = null

    @get:Column(name = "avatar_url")
    var avatarUrl: String? = null

    constructor()
    constructor(fullName: String?, email: String?, avatarUrl: String?) {
        this.fullName = fullName
        this.email = email
        this.avatarUrl = avatarUrl
    }
}