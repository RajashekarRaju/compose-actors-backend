package com.developersbreach.composeactors.watchlistPerson.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "watchlist_people")
data class PersonWatchlistDocument(
    @Id val id: String,
    val userId: String,
    val personId: Int,
    val personName: String,
    val personProfileUrl: String?,
)