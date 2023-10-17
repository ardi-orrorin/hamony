package com.hamony.boot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig  {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.cors {
            it.configurationSource {
                val config: CorsConfiguration  = CorsConfiguration()
                config.addAllowedOrigin("*")
                config.addAllowedHeader("*")
                config.addAllowedMethod("*")
                config
            }
        }

//        http.csrf().disable()
//            .authorizeHttpRequests()
//            .anyRequest("/")
//            .authenticated()
        return http.build()

    }

}