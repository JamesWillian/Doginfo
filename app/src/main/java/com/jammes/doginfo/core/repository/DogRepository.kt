package com.jammes.doginfo.core.repository

import android.util.Log
import com.jammes.doginfo.core.model.DogDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApi: DogApiService
) {

    suspend fun getDogsList(name: String): Result<List<DogDomain>> {
        return try {
            val response = dogApi.getDogs(name = name)

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    val dogs = response.body() ?: emptyList()
                    Result.success(dogs)
                } else {
                    Log.e("DogRepository","Não foi possivel obter resposta da API. Error: ${response.message()}")
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            Log.e("DogRepository","error: ${e.message}")
            Result.failure(e)
        }
    }
}