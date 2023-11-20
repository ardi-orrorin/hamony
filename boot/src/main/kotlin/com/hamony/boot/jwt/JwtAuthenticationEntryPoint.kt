package com.hamony.boot.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component


@Component
class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {

//        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED, request!!.getAttribute("exception").toString())
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED)

    }
}