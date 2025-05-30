package com.developersbreach.composeactors.watchlistPerson.service

import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistDocument
import com.developersbreach.composeactors.watchlistPerson.data.PersonWatchlistRepository
import io.mockk.every
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
}