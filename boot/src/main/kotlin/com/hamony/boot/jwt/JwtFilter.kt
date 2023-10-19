package com.hamony.boot.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private var tokenProvider: TokenProvider,

): OncePerRequestFilter() {

    private val AUTHORIZATION_HEADER: String = "Authorization"

    @Value("\${jwt.grantType}")
    private lateinit var GRANT_TYPE: String

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt: String? = resolveToken(request)
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt.toString())){
            val authentication: Authentication = tokenProvider.getAuthentication(jwt.toString())
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest?): String?{
        val bearerToken: String? = request?.getHeader(AUTHORIZATION_HEADER)

        if (bearerToken != null) {
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(bearerToken)){
                return bearerToken.substring(7)
            }
        }
        return null
    }

}