package com.jammes.doginfo.di

import com.jammes.doginfo.BuildConfig
import com.jammes.doginfo.core.repository.ApiKeyInterceptor
import com.jammes.doginfo.core.repository.DogApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    @Provides
    @Singleton
    fun provideApiKeyInteceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(BuildConfig.API_NINJA_KEY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideDogApi(okHttpClient: OkHttpClient): DogApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }

}