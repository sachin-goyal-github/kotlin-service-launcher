package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class UserInfo(
    var id: @NotNull @Positive Long, var email: @NotBlank String, var fullName: String?, var avatarUrl: String?
)