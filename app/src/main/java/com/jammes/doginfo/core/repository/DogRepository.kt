package com.jammes.doginfo.core.repository

import com.jammes.doginfo.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApi: DogApiService
) {

    suspend fun getDogsList(name: String): Result<List<Dog>> {
        return try {
            val response = dogApi.getDogs(name = name)

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    val dogs = response.body() ?: emptyList()
                    Result.success(dogs)
                } else {
                    Result.failure(Exception("Erro ao obter resposta"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}