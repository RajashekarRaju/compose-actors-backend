package com.developersbreach.composeactors.watchlistMovie.web

import com.developersbreach.composeactors.security.CurrentUserId
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto
import com.developersbreach.composeactors.watchlistMovie.service.MovieWatchlistService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/watchlist/movies")
class MovieWatchlistController(
    private val service: MovieWatchlistService,
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
    ): MovieWatchlistDocument {
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