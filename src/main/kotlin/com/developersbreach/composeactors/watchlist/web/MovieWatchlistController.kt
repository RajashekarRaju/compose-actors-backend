package com.developersbreach.composeactors.watchlist.web

import com.developersbreach.composeactors.security.CurrentUserId
import com.developersbreach.composeactors.watchlist.data.WatchlistMovieDocument
import com.developersbreach.composeactors.watchlist.dto.MovieDto
import com.developersbreach.composeactors.watchlist.service.WatchlistService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/watchlist")
class MovieWatchlistController(
    private val service: WatchlistService,
    private val currentUserId: CurrentUserId
) {
    @GetMapping
    fun getWatchlist(
        token: JwtAuthenticationToken
    ): List<MovieDto> {
        return service.list(currentUserId(token))
    }

    @PostMapping
    fun upsertMovie(
        token: JwtAuthenticationToken,
        @RequestBody dto: MovieDto
    ): WatchlistMovieDocument {
        return service.addOrUpdate(currentUserId(token), dto)
    }

    @DeleteMapping("/{movieId}")
    fun deleteMovie(
        token: JwtAuthenticationToken,
        @PathVariable movieId: Int
    ) {
        return service.remove(currentUserId(token), movieId)
    }
}