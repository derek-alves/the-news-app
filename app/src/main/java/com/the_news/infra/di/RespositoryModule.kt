package com.the_news.infra.di

import com.the_news.data.datasource.NewsLocalDatasource
import com.the_news.data.datasource.NewsRemoteDatasource
import com.the_news.data.respitory.NewsRepositoryImpl
import com.the_news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesNewsRepository(
        newsRemoteDatasource: NewsRemoteDatasource,
        newsLocalDatasource: NewsLocalDatasource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDatasource, newsLocalDatasource)
    }
}