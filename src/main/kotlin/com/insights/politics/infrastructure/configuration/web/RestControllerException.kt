package com.insights.politics.infrastructure.configuration.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class RestControllerExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Map<String, Any>> {
        val errorResponse = mutableMapOf<String, Any>()
        errorResponse["status"] = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorResponse["error"] = "Internal Server Error"
        errorResponse["message"] = ex.message ?: "An unknown error occurred"
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
