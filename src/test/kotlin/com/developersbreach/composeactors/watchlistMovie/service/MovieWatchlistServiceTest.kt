package com.developersbreach.composeactors.watchlistMovie.service

import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistRepository
import com.developersbreach.composeactors.watchlistMovie.dto.MovieDto
import io.mockk.every
import io.mockk.justRun
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

    @Test
    fun `addOrUpdate delegates to repository and returns saved document`() {
        val movieDto = MovieDto(
            movieId = 3,
            movieName = "Life",
            moviePosterUrl = null,
            movieBannerUrl = null
        )

        val document = MovieWatchlistDocument(
            id = "${userId}_${movieDto.movieId}",
            userId = userId,
            movieId = movieDto.movieId,
            movieName = movieDto.movieName,
            moviePosterUrl = movieDto.moviePosterUrl,
            movieBannerUrl = movieDto.movieBannerUrl
        )

        every {
            repository.save(any())
        } returns document

        val result = service.addOrUpdate(userId, movieDto)

        assertThat(result).isEqualTo(document)
        verify(exactly = 1) {
            repository.save(any())
        }
    }

    @Test
    fun `remove delegates to repository`() {
        val movieId = 3

        justRun {
            repository.deleteByUserIdAndMovieId(userId, movieId)
        }

        service.remove(userId, movieId)

        verify(exactly = 1) {
            repository.deleteByUserIdAndMovieId(userId, movieId)
        }
    }
}