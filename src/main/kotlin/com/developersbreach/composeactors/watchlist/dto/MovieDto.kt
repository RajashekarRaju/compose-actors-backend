package com.developersbreach.composeactors.watchlist.dto

data class MovieDto(
    val movieId: Int,
    val movieName: String,
    val moviePosterUrl: String?,
    val movieBannerUrl: String?
)