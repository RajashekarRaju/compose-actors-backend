package com.developersbreach.composeactors.watchlist.service

import com.developersbreach.composeactors.watchlist.data.WatchlistMovieDocument
import com.developersbreach.composeactors.watchlist.data.WatchlistRepository
import com.developersbreach.composeactors.watchlist.dto.MovieDto
import com.developersbreach.composeactors.watchlist.data.toDocument
import com.developersbreach.composeactors.watchlist.data.toDto
import org.springframework.stereotype.Service

@Service
class WatchlistService(
    private val repository: WatchlistRepository
) {
    fun list(
        userId: String
    ): List<MovieDto> {
        return repository.findAllByUserId(userId).map {
            it.toDto()
        }
    }

    fun addOrUpdate(
        userId: String,
        movieDto: MovieDto
    ): WatchlistMovieDocument {
        return repository.save(movieDto.toDocument(userId))
    }

    fun remove(
        userId: String,
        movieId: Int
    ) {
        return repository.deleteByUserIdAndMovieId(userId, movieId)
    }
}