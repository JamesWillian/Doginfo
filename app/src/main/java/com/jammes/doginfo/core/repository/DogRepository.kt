package com.jammes.doginfo.core.repository

import com.jammes.doginfo.Dog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class DogRepository {

    private val dogApi: DogApiService = DogApiService()

    suspend fun getDogsList(name: String): Result<List<Dog>> {
        return try {
            val response = dogApi.getDogs(name = name)
            if (response.isSuccessful) {
                val dogs = response.body()?.dogs ?: emptyList()
                Result.success(dogs)
            } else {
                Result.failure(Exception("Erro ao obter resposta"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}