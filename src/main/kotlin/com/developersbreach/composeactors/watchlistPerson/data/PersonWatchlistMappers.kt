package com.developersbreach.composeactors.watchlistPerson.data

import com.developersbreach.composeactors.watchlistPerson.dto.PersonDto

fun PersonDto.toDocument(userId: String) = PersonWatchlistDocument(
    id = "$userId|$personId",
    userId = userId,
    personId = personId,
    personName = personName,
    personProfileUrl = personProfileUrl
)

fun PersonWatchlistDocument.toDto() = PersonDto(
    personId = personId,
    personName = personName,
    personProfileUrl = personProfileUrl
)