package com.jammes.doginfo.core.repository

import com.jammes.doginfo.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    fun provideApiKeyInteceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(BuildConfig.API_NINJA_KEY)
    }

    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    fun provideDogApi(okHttpClient: OkHttpClient): DogApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }

}