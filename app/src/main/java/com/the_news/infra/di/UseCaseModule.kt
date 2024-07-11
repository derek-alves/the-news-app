package com.the_news.infra.di

import com.the_news.domain.repository.NewsRepository
import com.the_news.domain.usecase.GetNewsHeadLinesUseCase
import com.the_news.domain.usecase.GetSavedNewsUseCase
import com.the_news.domain.usecase.GetSearchedNewsUseCase
import com.the_news.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetNewsHeadLinesUseCase(newsRepository: NewsRepository): GetNewsHeadLinesUseCase {
        return GetNewsHeadLinesUseCase(newsRepository)
    }

    @Provides
    fun provideGetSearchedNewsHeadLinesUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Provides
    fun provideSaveNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(newsRepository)
    }

    @Provides
    fun getSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }


}