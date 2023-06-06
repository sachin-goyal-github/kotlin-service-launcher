package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank

class SignupRequest {
    var email: @NotBlank String? = null
    var name: @NotBlank String? = null
    var avatarUrl: String? = null
    var password: @NotBlank String? = null

    constructor()
    constructor(email: String?, name: String?, avatarUrl: String?, password: String?) {
        this.email = email
        this.name = name
        this.avatarUrl = avatarUrl
        this.password = password
    }
}