package com.the_news.infra.di

import com.the_news.BuildConfig
import com.the_news.infra.api.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpClientModule {

    @Singleton
    @Provides
    fun provideClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(
                BuildConfig.API_URL
            ).build()
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsAPIService {
        return retrofit.create(NewsAPIService::class.java)
    }

}