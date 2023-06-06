package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class UserInfo {
    var id: @NotNull @Positive Long? = null
    var email: @NotBlank String? = null
    var fullName: String? = null
    var avatarUrl: String? = null

    constructor()
    constructor(id: Long?, email: String?, fullName: String?, avatarUrl: String?) {
        this.id = id
        this.email = email
        this.fullName = fullName
        this.avatarUrl = avatarUrl
    }
}