package com.developersbreach.composeactors.common

data class PagedResponse<T>(
    val page: Int,
    val totalPages: Int,
    val results: List<T>
)