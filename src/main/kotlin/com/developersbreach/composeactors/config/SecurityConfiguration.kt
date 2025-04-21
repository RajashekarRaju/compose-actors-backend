package com.developersbreach.composeactors.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        return httpSecurity.authorizeHttpRequests {
            it.requestMatchers("/public")
                .permitAll()
                .anyRequest()
                .authenticated()
        }.oauth2ResourceServer {
            it.jwt(Customizer.withDefaults())
        }.build()
    }
}