package com.developersbreach.composeactors.watchlistPerson.web

import com.developersbreach.composeactors.common.PagedResponse
import com.developersbreach.composeactors.security.CurrentUserId
import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistDocument
import com.developersbreach.composeactors.watchlistPerson.dto.PersonDto
import com.developersbreach.composeactors.watchlistPerson.service.PersonWatchlistService
import org.springframework.data.domain.PageRequest
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/watchlist/people")
class PersonWatchlistController(
    private val service: PersonWatchlistService,
    private val currentUserId: CurrentUserId
) {
    @GetMapping
    fun getWatchlist(
        token: JwtAuthenticationToken,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PagedResponse<PersonDto> {
        return service.listPaged(
            userId = currentUserId(token = token),
            pageable = PageRequest.of(page, size)
        )
    }

    @PostMapping
    fun upsertPerson(
        token: JwtAuthenticationToken,
        @RequestBody dto: PersonDto
    ): PersonWatchlistDocument {
        return service.addOrUpdate(currentUserId(token), dto)
    }

    @DeleteMapping("/{personId}")
    fun deletePerson(
        token: JwtAuthenticationToken,
        @PathVariable personId: Int
    ) {
        return service.remove(currentUserId(token), personId)
    }
}