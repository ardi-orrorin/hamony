package com.hamony.boot.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component


@Component
class JwtAccessDeniedHandler: AccessDeniedHandler {

    val log: Logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {


        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, request!!.getAttribute("exception").toString())
//        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}