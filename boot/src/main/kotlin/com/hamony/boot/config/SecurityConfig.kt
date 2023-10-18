package com.hamony.boot.config

import com.hamony.boot.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig(
    var tokenProvider: TokenProvider
)  {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain  {

        http.cors {
            it.configurationSource {
                val config: CorsConfiguration  = CorsConfiguration()
                config.addAllowedOrigin("*")
                config.addAllowedHeader("*")
                config.addAllowedMethod("*")
                config
            }
        }

        http.apply(JwtSecurityConfig(tokenProvider))

        return http.build()

    }



}