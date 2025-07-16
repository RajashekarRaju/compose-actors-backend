package com.developersbreach.composeactors.watchlistPerson.service

import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistDocument
import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistRepository
import com.developersbreach.composeactors.watchlistPerson.dto.PersonDto
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class PersonWatchlistServiceTest {

    private val repository: PersonWatchlistRepository = mockk()
    private val service = PersonWatchlistService(repository)
    private val userId = "111-222"

    private val documents = listOf(
        PersonWatchlistDocument(
            id = "1",
            userId = userId,
            personId = 5,
            personName = "Ray",
            personProfileUrl = null
        ),
    )

    @Test
    fun `listPaged delegates to repository and maps result`() {
        val pageable = PageRequest.of(0, 2)
        val data = PageImpl(documents, pageable, 1)

        every {
            repository.findAllByUserId(userId, pageable)
        } returns data

        val result = service.listPaged(userId, pageable)

        assertThat(result.totalPages).isEqualTo(1)
        assertThat(result.results).hasSize(1)
        verify(exactly = 1) {
            repository.findAllByUserId(userId, pageable)
        }
    }

    @Test
    fun `addOrUpdate delegates to repository and returns saved document`() {
        val personDto = PersonDto(
            personId = 5,
            personName = "Ray",
            personProfileUrl = null
        )

        val document = PersonWatchlistDocument(
            id = "${userId}_${personDto.personId}",
            userId = userId,
            personId = personDto.personId,
            personName = personDto.personName,
            personProfileUrl = personDto.personProfileUrl
        )

        every {
            repository.save(any())
        } returns document

        val result = service.addOrUpdate(userId, personDto)

        assertThat(result).isEqualTo(document)
        verify(exactly = 1) {
            repository.save(any())
        }
    }

    @Test
    fun `remove delegates to repository`() {
        val personId = 5

        justRun {
            repository.deleteByUserIdAndPersonId(userId, personId)
        }

        service.remove(userId, personId)

        verify(exactly = 1) {
            repository.deleteByUserIdAndPersonId(userId, personId)
        }
    }
}