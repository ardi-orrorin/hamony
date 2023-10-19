package com.hamony.boot.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionAdvice {

    @ExceptionHandler(TokenException::class)
    fun exceptionHandler(e: TokenException): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);
    }

    @ExceptionHandler(VerifyPasswordException::class)
    fun exceptionHandler(e: VerifyPasswordException): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);
    }
    @ExceptionHandler(ExistUserException::class)
    fun exceptionHandler(e: ExistUserException): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
    }
}