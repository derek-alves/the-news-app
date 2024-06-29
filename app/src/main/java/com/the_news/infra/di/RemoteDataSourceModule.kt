package com.the_news.infra.di

import com.the_news.data.datasource.NewsRemoteDataSourceImpl
import com.the_news.data.datasource.NewsRemoteDatasource
import com.the_news.infra.api.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {


    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDatasource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}