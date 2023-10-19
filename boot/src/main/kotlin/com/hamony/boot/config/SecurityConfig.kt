package com.hamony.boot.config

import com.hamony.boot.jwt.JwtAuthenticationEntryPoint
import com.hamony.boot.jwt.TokenProvider
import com.hamony.boot.jwt.JwtAccessDeniedHandler
import org.springframework.beans.factory.annotation.Value
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
    val tokenProvider: TokenProvider,
    val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    val jwtAccessDeniedHandler: JwtAccessDeniedHandler,

    @Value("\${client.server.url}")
    val CLIENT_SERVER_URL: String
)  {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain  {

        http.cors {
            it.configurationSource {
                val config: CorsConfiguration  = CorsConfiguration()
                config.addAllowedOrigin(CLIENT_SERVER_URL)
                config.addAllowedHeader("*")
                config.addAllowedMethod("*")
                config
            }
        }

        http.csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)


        http.authorizeHttpRequests()
            .requestMatchers("/user/**").permitAll()
            .anyRequest().authenticated()


        http.apply(JwtSecurityConfig(tokenProvider))

        return http.build()

    }



}