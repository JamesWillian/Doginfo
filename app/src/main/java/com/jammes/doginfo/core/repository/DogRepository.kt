package com.jammes.doginfo.core.repository

import com.jammes.doginfo.core.model.DogDomain

interface DogRepository {

    suspend fun fetchAll(): List<DogDomain>

    suspend fun fetchByName(name: String): List<DogDomain>

    suspend fun fetchById(dogId: String): DogDomain

    suspend fun insert(dog: DogDomain)
}