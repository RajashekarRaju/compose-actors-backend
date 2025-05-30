package com.developersbreach.composeactors.watchlistPerson.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonWatchlistRepository : MongoRepository<PersonWatchlistDocument, String> {

    fun findAllByUserId(
        userId: String,
        pageable: Pageable
    ): Page<PersonWatchlistDocument>

    fun deleteByUserIdAndPersonId(
        userId: String,
        personId: Int
    )
}