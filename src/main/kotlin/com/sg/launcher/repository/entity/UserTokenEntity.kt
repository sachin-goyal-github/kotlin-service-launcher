package com.sg.launcher.repository.entity

import javax.persistence.*

@Entity
@Table(name = "user_token")
class UserTokenEntity {
    @get:Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @get:Column(name = "user_id")
    var userId: Long? = null

    @get:Column(name = "user_token")
    var userToken: String? = null

    constructor()
    constructor(userId: Long?, userToken: String?) {
        this.userId = userId
        this.userToken = userToken
    }
}