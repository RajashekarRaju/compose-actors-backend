package com.developersbreach.composeactors.watchlistMovie.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "watchlist_movies")
data class MovieWatchlistDocument(
    @Id val id: String,
    val userId: String,
    val movieId: Int,
    val movieName: String,
    val moviePosterUrl: String?,
    val movieBannerUrl: String?
)