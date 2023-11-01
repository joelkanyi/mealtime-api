package com.joelkanyi.mealtime.api.mealtimeapi.utils

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException

@RestControllerAdvice
class DefaultExceptionHandler {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(
        e: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Not found",
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException, request: HttpServletRequest): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Bad request",
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleConflict(e: IllegalStateException, request: HttpServletRequest): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Conflict",
                status = HttpStatus.CONFLICT.value(),
                error = HttpStatus.CONFLICT.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(UnsupportedOperationException::class)
    fun handleUnsupportedOperation(
        e: UnsupportedOperationException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Unsupported operation",
                status = HttpStatus.NOT_IMPLEMENTED.value(),
                error = HttpStatus.NOT_IMPLEMENTED.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.NOT_IMPLEMENTED
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleInternalError(e: Exception, request: HttpServletRequest): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Internal server error",
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(e: AccessDeniedException, request: HttpServletRequest): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                message = e.message ?: "Access denied",
                status = HttpStatus.FORBIDDEN.value(),
                error = HttpStatus.FORBIDDEN.name,
                path = request.servletPath,
                timestamp = System.currentTimeMillis()
            ),
            HttpStatus.FORBIDDEN
        )
    }
}