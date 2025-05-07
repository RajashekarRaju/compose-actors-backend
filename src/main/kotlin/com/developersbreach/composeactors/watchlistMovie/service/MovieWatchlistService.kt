package com.developersbreach.composeactors.watchlistMovie.service

import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistRepository
import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto
import com.developersbreach.composeactors.watchlistMovie.data.toDocument
import com.developersbreach.composeactors.watchlistMovie.data.toDto
import org.springframework.stereotype.Service

@Service
class MovieWatchlistService(
    private val repository: MovieWatchlistRepository
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
    ): MovieWatchlistDocument {
        return repository.save(movieDto.toDocument(userId))
    }

    fun remove(
        userId: String,
        movieId: Int
    ) {
        return repository.deleteByUserIdAndMovieId(userId, movieId)
    }
}