package com.developersbreach.composeactors.watchlistMovie.dto

data class MovieDto(
    val movieId: Int,
    val movieName: String,
    val moviePosterUrl: String?,
    val movieBannerUrl: String?
)