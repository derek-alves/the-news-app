package com.the_news.infra.di

import android.app.Application
import com.the_news.domain.usecase.GetNewsHeadLinesUseCase
import com.the_news.domain.usecase.GetSearchedNewsUseCase
import com.the_news.domain.usecase.SaveNewsUseCase
import com.the_news.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    fun providesNewsViewModelFactory(
        application: Application,
        getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsHeadLinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase
        )

    }
}