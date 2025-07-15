package com.developersbreach.composeactors.profile.web

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController {

    @GetMapping("/public")
    fun public(): String {
        return "Test for public api"
    }

    @GetMapping("/public/health")
    fun health(): Map<String, String> {
        return mapOf("status" to "UP")
    }

    @GetMapping("/profile")
    fun profile(
        jwtAuthenticationToken: JwtAuthenticationToken
    ): String {
        val username = jwtAuthenticationToken.token.claims["username"]
        return "Login successful for user $username"
    }
}