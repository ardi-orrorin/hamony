package com.hamony.boot.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionAdvice {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)!!

    fun logError(e:String) {
        log.error("[ApiExceptionAdvice](exceptionHandler) errorMesage : {}", e)
    }

    @ExceptionHandler(TokenException::class)
    fun exceptionHandler(e: TokenException): ResponseEntity<String>{
        logError(e.message as String)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message);
    }

    @ExceptionHandler(VerifyPasswordException::class)
    fun exceptionHandler(e: VerifyPasswordException): ResponseEntity<String>{
        logError(e.message as String)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);
    }
    @ExceptionHandler(ExistUserException::class)
    fun exceptionHandler(e: ExistUserException): ResponseEntity<String>{
        logError(e.message as String)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
    }

    @ExceptionHandler(InvalidFile::class)
    fun exceptionHandler(e: InvalidFile): ResponseEntity<String>{
        logError(e.message as String)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
    }
}