package com.developersbreach.composeactors.watchlistMovie.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface MovieWatchlistRepository : MongoRepository<MovieWatchlistDocument, String> {

    fun findAllByUserId(
        userId: String,
        pageable: Pageable
    ): Page<MovieWatchlistDocument>

    fun deleteByUserIdAndMovieId(
        userId: String,
        movieId: Int
    )
}