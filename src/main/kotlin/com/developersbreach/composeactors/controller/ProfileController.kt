package com.developersbreach.composeactors.controller

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController {

    @GetMapping("/public")
    fun public(): String {
        return "Test for public api"
    }

    @GetMapping("/profile")
    fun profile(
        jwtAuthenticationToken: JwtAuthenticationToken
    ): String {
        val username = jwtAuthenticationToken.token.claims["username"]
        return "Login successful"
    }
}