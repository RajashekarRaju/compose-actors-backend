package com.developersbreach.composeactors.watchlistMovie.web

import com.developersbreach.composeactors.common.PagedResponse
import com.developersbreach.composeactors.security.CurrentUserId
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto
import com.developersbreach.composeactors.watchlistMovie.service.MovieWatchlistService
import org.springframework.data.domain.PageRequest
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/watchlist/movies")
class MovieWatchlistController(
    private val service: MovieWatchlistService,
    private val currentUserId: CurrentUserId
) {
    @GetMapping
    fun getWatchlist(
        token: JwtAuthenticationToken,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PagedResponse<MovieDto> {
        return service.listPaged(
            userId = currentUserId(token = token),
            pageable = PageRequest.of(page, size)
        )
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