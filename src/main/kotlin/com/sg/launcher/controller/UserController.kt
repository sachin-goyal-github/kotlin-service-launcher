package com.sg.launcher.controller

import com.sg.launcher.service.UserService
import com.sg.launcher.transfer.SignupRequest
import com.sg.launcher.transfer.UserCredentials
import com.sg.launcher.transfer.UserInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @Autowired
    var userService: UserService? = null
    @CrossOrigin
    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.PUT])
    fun authenticate(@RequestBody credentials: UserCredentials?): String? {
        return userService!!.authenticate(credentials)
    }

    @CrossOrigin
    @RequestMapping(value = ["/signup"], method = [RequestMethod.PUT])
    fun signup(@RequestBody signupRequest: SignupRequest): String? {
        LOGGER.info("Signing up {}", signupRequest.email)
        return userService!!.signup(signupRequest)
    }

    @CrossOrigin
    @RequestMapping(value = ["/validateUserToken/{userToken}"], method = [RequestMethod.GET])
    fun validateUserToken(@PathVariable("userToken") userToken: String?): Boolean {
        return userService!!.validateUserToken(userToken)
    }

    @CrossOrigin
    @RequestMapping(value = ["/userForToken/{userToken}"], method = [RequestMethod.GET])
    fun userForToken(@PathVariable("userToken") userToken: String?): UserInfo? {
        return userService!!.getUserForToken(userToken)
    }

    @CrossOrigin
    @RequestMapping(value = ["/users"], method = [RequestMethod.GET])
    fun users(): List<UserInfo> {
        return userService!!.allUsers
    }

    @CrossOrigin
    @RequestMapping(value = ["/userTokens/{userId}"], method = [RequestMethod.GET])
    fun userTokens(@PathVariable("userId") userId: Long?): Set<String> {
        return userService!!.getUserTokens(userId)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }
}