package com.sg.launcher.transfer

import javax.validation.constraints.NotBlank

data class UserCredentials(var userName: @NotBlank String, var password: @NotBlank String)