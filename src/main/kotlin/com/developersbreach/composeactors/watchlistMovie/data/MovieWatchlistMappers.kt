package com.developersbreach.composeactors.watchlistMovie.data

import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto

fun MovieDto.toDocument(userId: String) = MovieWatchlistDocument(
    id = "$userId|$movieId",
    userId = userId,
    movieId = movieId,
    movieName = movieName,
    moviePosterUrl = moviePosterUrl,
    movieBannerUrl = movieBannerUrl
)

fun MovieWatchlistDocument.toDto() = MovieDto(
    movieId = movieId,
    movieName = movieName,
    moviePosterUrl = moviePosterUrl,
    movieBannerUrl = movieBannerUrl
)