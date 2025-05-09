package com.developersbreach.composeactors.watchlistMovie.service

import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class MovieWatchlistServiceTest {

    private val repository: MovieWatchlistRepository = mockk()
    private val service = MovieWatchlistService(repository)
    private val userId = "111-222"

    private val documents = listOf(
        MovieWatchlistDocument(
            id = "1",
            userId = userId,
            movieId = 3,
            movieName = "Life",
            moviePosterUrl = null,
            movieBannerUrl = null
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