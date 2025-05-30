package com.developersbreach.composeactors.watchlistPerson.service

import com.developersbreach.composeactors.common.PagedResponse
import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistDocument
import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistRepository
import com.developersbreach.composeactors.watchlistPerson.data.toDocument
import com.developersbreach.composeactors.watchlistPerson.data.toDto
import com.developersbreach.composeactors.watchlistPerson.dto.PersonDto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PersonWatchlistService(
    private val repository: PersonWatchlistRepository
) {
    fun listPaged(
        userId: String,
        pageable: Pageable
    ): PagedResponse<PersonDto> {
        val pageResult = repository.findAllByUserId(userId, pageable)
        return PagedResponse(
            page = pageResult.number + 1,
            totalPages = pageResult.totalPages,
            results = pageResult.content.map { it.toDto() }
        )
    }

    fun addOrUpdate(
        userId: String,
        personDto: PersonDto
    ): PersonWatchlistDocument {
        return repository.save(personDto.toDocument(userId))
    }

    fun remove(
        userId: String,
        personId: Int
    ) {
        return repository.deleteByUserIdAndPersonId(userId, personId)
    }
}