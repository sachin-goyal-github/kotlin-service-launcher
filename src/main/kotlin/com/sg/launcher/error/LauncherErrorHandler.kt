package com.sg.launcher.error

import org.apache.logging.log4j.util.Strings
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.function.Function
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.Path

@ControllerAdvice
class LauncherErrorHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        val result = ex.bindingResult
        val error = result.fieldError
        var errorMessage: String? = null
        if (error != null) {
            errorMessage = error.field + " - " + error.defaultMessage
        }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<String> {
        val getLast = Function { path: Path? ->
            if (path == null) return@Function ""
            var field = ""
            for (node in path) {
                field = node.name
            }
            field
        }
        val constraintViolations = ex.constraintViolations
        val messages: MutableSet<String?> = HashSet(constraintViolations.size)
        messages.addAll(constraintViolations.stream()
            .map { constraintViolation: ConstraintViolation<*> ->
                String.format(
                    "%s %s",
                    getLast.apply(constraintViolation.propertyPath),
                    constraintViolation.message
                )
            }
            .collect(Collectors.toList()))
        return ResponseEntity(Strings.join(messages, '\n'), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalAccessException::class)
    fun handleIllegalAccessException(ex: IllegalAccessException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
