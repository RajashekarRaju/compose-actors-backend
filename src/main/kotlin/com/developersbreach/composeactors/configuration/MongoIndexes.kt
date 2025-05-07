package com.developersbreach.composeactors.configuration

import com.developersbreach.composeactors.watchlistMovie.data.MovieWatchlistDocument
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index

@Configuration
class MongoIndexes(
    private val template: MongoTemplate
) : InitializingBean {
    override fun afterPropertiesSet() {
        val index = Index()
            .on("userId", Sort.Direction.ASC)
            .on("movieId", Sort.Direction.ASC)
            .unique()

        template
            .indexOps(MovieWatchlistDocument::class.java)
            .ensureIndex(index)
    }
}