package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank

data class SignupRequest(
    var email: @NotBlank String, var name: @NotBlank String, var avatarUrl: String, var password: @NotBlank String
)