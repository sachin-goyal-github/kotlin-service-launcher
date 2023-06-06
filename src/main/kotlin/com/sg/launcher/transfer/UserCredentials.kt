package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank

class UserCredentials {
    var userName: @NotBlank String? = null
    var password: @NotBlank String? = null

    constructor()
    constructor(userName: String?, password: String?) {
        this.userName = userName
        this.password = password
    }
}