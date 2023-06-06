package com.sg.launcher.repository.entity

import javax.persistence.*

@Entity
@Table(name = "user_info")
class UserInfoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "full_name")
    var fullName: String? = null

    @Column(name = "avatar_url")
    var avatarUrl: String? = null

    constructor()
    constructor(fullName: String?, email: String?, avatarUrl: String?) {
        this.fullName = fullName
        this.email = email
        this.avatarUrl = avatarUrl
    }
}