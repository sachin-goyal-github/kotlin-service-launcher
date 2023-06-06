package com.sg.launcher.service

import com.sg.launcher.repository.UserCredentialsRepository
import com.sg.launcher.repository.UserInfoRepository
import com.sg.launcher.repository.UserTokenRepository
import com.sg.launcher.repository.entity.UserCredentialsEntity
import com.sg.launcher.repository.entity.UserInfoEntity
import com.sg.launcher.repository.entity.UserTokenEntity
import com.sg.launcher.transfer.SignupRequest
import com.sg.launcher.transfer.UserCredentials
import com.sg.launcher.transfer.UserInfo
import com.sg.launcher.util.Passwords
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.validation.annotation.Validated
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service
@Validated
class UserService {
    @Autowired
    var userInfoRepository: UserInfoRepository? = null

    @Autowired
    var userCredentialsRepository: UserCredentialsRepository? = null

    @Autowired
    var userTokenRepository: UserTokenRepository? = null
    fun authenticate(passedCredentials: @Valid @NotNull UserCredentials?): String? {
        val userInfoEntity = userInfoRepository!!.findByEmail(passedCredentials!!.userName)
            ?: throw IllegalArgumentException("Invalid user name, could not find user with such user name")
        val existingCredentials = userCredentialsRepository!!.findByUserId(userInfoEntity.id)
            ?: throw IllegalStateException("No credentials are stored for the user, please signup again")
        require(
            Passwords.isExpectedPassword(
                passedCredentials.password.toString(),
                existingCredentials.salt,
                existingCredentials.passwordHash!!
            )
        ) { "Invalid username/password" }
        val userToken = userTokenRepository!!.save(
            UserTokenEntity(userInfoEntity.id, UUID.randomUUID().toString())
        )
        return userToken.userToken
    }

    fun signup(signupRequest: @Valid @NotNull SignupRequest?): String? {
        require(userInfoRepository!!.findByEmail(signupRequest!!.email) == null) { "User with same email already exists" }
        val user = userInfoRepository!!.save(
            UserInfoEntity(
                signupRequest.name,
                signupRequest.email, signupRequest.avatarUrl
            )
        )
        val salt = Passwords.nextSalt
        val hash = Passwords.hash(signupRequest.password.toString(), salt)
        userCredentialsRepository!!.save(
            UserCredentialsEntity(
                user.id,
                user.email,
                salt,
                hash
            )
        )
        val userToken = userTokenRepository!!.save(
            UserTokenEntity(
                user.id,
                UUID.randomUUID().toString()
            )
        )
        return userToken.userToken
    }

    fun validateUserToken(userToken: String?): Boolean {
        return if (!StringUtils.hasLength(userToken)) false else userTokenRepository!!.findByUserToken(
            userToken
        ) != null
    }

    fun getUserForToken(userToken: String?): UserInfo? {
        val userTokenEntity = userTokenRepository!!.findByUserToken(userToken)
        if (userTokenEntity != null) {
            val userInfoEntityOptional = userInfoRepository!!.findById(userTokenEntity.userId!!)
            if (userInfoEntityOptional.isPresent) {
                val userInfoEntity = userInfoEntityOptional.get()
                return UserInfo(
                    userInfoEntity.id, userInfoEntity.email,
                    userInfoEntity.fullName, userInfoEntity.avatarUrl
                )
            }
        }
        return null
    }

    val allUsers: List<UserInfo>
        get() {
            val userInfoEntities = userInfoRepository!!.findAll()
            val userInfos: MutableList<UserInfo> = ArrayList()
            for (userInfoEntity in userInfoEntities) {
                userInfos.add(
                    UserInfo(
                        userInfoEntity!!.id, userInfoEntity.email,
                        userInfoEntity.fullName, userInfoEntity.avatarUrl
                    )
                )
            }
            return userInfos
        }

    fun getUserTokens(userId: Long?): Set<String> {
        return emptySet()
//        val userTokens = userTokenRepository!!.findByUserId(userId) ?: return Collections.emptySet()
//        return userTokens.stream()
//            .map(UserTokenEntity::userToken)
//            .collect(Collectors.toSet<String?>())
    }
}