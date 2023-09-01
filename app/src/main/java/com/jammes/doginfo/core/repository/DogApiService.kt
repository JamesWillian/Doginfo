package com.jammes.doginfo.core.repository

import com.jammes.doginfo.Dog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {

    @GET("dogs")
    suspend fun getDogs(
        @Query("name") name: String? = null,
        @Query("min_height") minHeight: Int? = null,
        @Query("max_height") maxHeight: Int? = null,
        @Query("min_weight") minWeight: Int? = null,
        @Query("max_weight") maxWeight: Int? = null,
        @Query("min_life_expectancy") minLifeExpectancy: Int? = null,
        @Query("max_life_expectancy") maxLifeExpectancy: Int? = null,
        @Query("shedding") shedding: Int? = null,
        @Query("barking") barking: Int? = null,
        @Query("energy") energy: Int? = null,
        @Query("protectiveness") protectiveness: Int? = null,
        @Query("trainability") trainability: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<List<Dog>>

}