package com.developersbreach.composeactors.watchlist.data

import org.springframework.data.mongodb.repository.MongoRepository

interface WatchlistRepository : MongoRepository<WatchlistMovieDocument, String> {

    fun findAllByUserId(
        userId: String
    ): List<WatchlistMovieDocument>

    fun deleteByUserIdAndMovieId(
        userId: String,
        movieId: Int
    )
}