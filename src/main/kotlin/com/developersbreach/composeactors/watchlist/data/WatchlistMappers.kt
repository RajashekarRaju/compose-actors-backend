package com.developersbreach.composeactors.watchlist.data

import com.developersbreach.composeactors.watchlist.dto.MovieDto

fun MovieDto.toDocument(userId: String) = WatchlistMovieDocument(
    id = "$userId|$movieId",
    userId = userId,
    movieId = movieId,
    movieName = movieName,
    moviePosterUrl = moviePosterUrl,
    movieBannerUrl = movieBannerUrl
)

fun WatchlistMovieDocument.toDto() = MovieDto(
    movieId = movieId,
    movieName = movieName,
    moviePosterUrl = moviePosterUrl,
    movieBannerUrl = movieBannerUrl
)