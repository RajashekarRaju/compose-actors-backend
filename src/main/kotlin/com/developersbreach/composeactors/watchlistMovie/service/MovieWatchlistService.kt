package com.developersbreach.composeactors.watchlistMovie.service

import com.developersbreach.composeactors.common.PagedResponse
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistRepository
import com.developersbreach.composeactors.watchlistMovie.data.toDocument
import com.developersbreach.composeactors.watchlistMovie.data.toDto
import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MovieWatchlistService(
    private val repository: MovieWatchlistRepository
) {
    fun listPaged(
        userId: String,
        pageable: Pageable
    ): PagedResponse<MovieDto> {
        val pageResult = repository.findAllByUserId(userId, pageable)
        return PagedResponse(
            page = pageResult.number + 1,
            totalPages = pageResult.totalPages,
            results = pageResult.content.map { it.toDto() }
        )
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