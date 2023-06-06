package com.sg.launcher.util

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * A utility class to hash passwords and check passwords vs hashed values. It uses a combination of hashing and unique
 * salt. The algorithm used is PBKDF2WithHmacSHA1 which, although not the best for hashing password (vs. bcrypt) is
 * still considered robust and [ recommended by NIST ](https://security.stackexchange.com/a/6415/12614).
 * The hashed value has 256 bits.
 */
object Passwords {
    private val RANDOM: Random = SecureRandom()
    private const val ITERATIONS = 10000
    private const val KEY_LENGTH = 256
    val nextSalt: ByteArray
        /**
         * Returns a random salt to be used to hash a password.
         *
         * @return a 16 bytes random salt
         */
        get() {
            val salt = ByteArray(16)
            RANDOM.nextBytes(salt)
            return salt
        }

    /**
     * Returns a salted and hashed password using the provided hash.<br></br>
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     *
     * @param password the password to be hashed
     * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
     * @return the hashed password with a pinch of salt
     */
    fun hash(password: String, salt: ByteArray?): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        return try {
            Arrays.fill(password.toCharArray(), Character.MIN_VALUE)
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            skf.generateSecret(spec).encoded
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Error while hashing a password: " + e.message, e)
        } catch (e: InvalidKeySpecException) {
            throw RuntimeException("Error while hashing a password: " + e.message, e)
        } finally {
            spec.clearPassword()
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false otherwise.<br></br>
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     *
     * @param password     the password to check
     * @param salt         the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     * @return true if the given password and salt match the hashed value, false otherwise
     */
    fun isExpectedPassword(password: String, salt: ByteArray?, expectedHash: ByteArray): Boolean {
        val pwdHash = hash(password, salt)
        Arrays.fill(password.toCharArray(), Character.MIN_VALUE)
        if (pwdHash.size != expectedHash.size) return false
        for (i in pwdHash.indices) {
            if (pwdHash[i] != expectedHash[i]) return false
        }
        return true
    }

    /**
     * Generates a random password of a given length, using letters and digits.
     *
     * @param length the length of the password
     * @return a random password
     */
    fun generateRandomPassword(length: Int): String {
        val sb = StringBuilder(length)
        for (i in 0 until length) {
            val c = RANDOM.nextInt(62)
            if (c <= 9) {
                sb.append(c.toString())
            } else if (c < 36) {
                sb.append(('a'.code + c - 10).toChar())
            } else {
                sb.append(('A'.code + c - 36).toChar())
            }
        }
        return sb.toString()
    }
}