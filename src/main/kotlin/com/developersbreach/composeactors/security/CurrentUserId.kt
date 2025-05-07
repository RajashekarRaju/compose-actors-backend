package com.developersbreach.composeactors.security

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component

@Component
class CurrentUserId {

    operator fun invoke(
        token: JwtAuthenticationToken
    ): String {
        return token.token.claims["username"] as String
    }
}