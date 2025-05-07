package com.developersbreach.composeactors.watchlistMovie.data

import org.springframework.data.mongodb.repository.MongoRepository

interface MovieWatchlistRepository : MongoRepository<MovieWatchlistDocument, String> {

    fun findAllByUserId(
        userId: String
    ): List<MovieWatchlistDocument>

    fun deleteByUserIdAndMovieId(
        userId: String,
        movieId: Int
    )
}