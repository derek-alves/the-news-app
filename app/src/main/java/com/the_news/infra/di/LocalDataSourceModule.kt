package com.the_news.infra.di

import com.the_news.data.datasource.NewsLocalDatasource
import com.the_news.data.datasource.NewsLocalDatasourceImpl
import com.the_news.infra.db.ArticleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDatasource {
        return NewsLocalDatasourceImpl(articleDAO)
    }

}