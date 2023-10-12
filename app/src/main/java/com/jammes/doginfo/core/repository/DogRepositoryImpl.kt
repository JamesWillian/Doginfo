package com.jammes.doginfo.core.repository

import android.util.Log
import com.jammes.doginfo.core.database.dao.DogDao
import com.jammes.doginfo.core.database.entity.Dog
import com.jammes.doginfo.core.model.DogDomain
import java.util.*
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dao: DogDao
) : DogRepository {

    private fun DogDomain.toDog() : Dog {
        return Dog(
            uuid = UUID.randomUUID().toString(),
            name = name,
            good_with_children = good_with_children,
            good_with_other_dogs = good_with_other_dogs,
            shedding = shedding,
            grooming = grooming,
            drooling = drooling,
            coat_length = coat_length,
            good_with_strangers = good_with_strangers,
            playfulness = playfulness,
            protectiveness = protectiveness,
            trainability = trainability,
            energy = energy,
            barking = barking,
            min_life_expectancy = min_life_expectancy,
            max_life_expectancy = max_life_expectancy,
            max_height_male = max_height_male,
            max_height_female = max_height_female,
            max_weight_male = max_weight_male,
            max_weight_female = max_weight_female,
            min_height_male = min_height_male,
            min_height_female = min_height_female,
            min_weight_male = min_weight_male,
            min_weight_female = min_weight_female,
            image_dir = ""
        )
    }

    private fun Dog.toDogDomain() : DogDomain {
        return DogDomain(
            name = name,
            image_link = "",
            good_with_children = good_with_children ?: 0,
            good_with_other_dogs = good_with_other_dogs ?: 0,
            shedding = shedding ?: 0,
            grooming = grooming ?: 0,
            drooling = drooling ?: 0,
            coat_length = coat_length ?: 0,
            good_with_strangers = good_with_strangers ?: 0,
            playfulness = playfulness ?: 0,
            protectiveness = protectiveness ?: 0,
            trainability = trainability ?: 0,
            energy = energy ?: 0,
            barking = barking ?: 0,
            min_life_expectancy = min_life_expectancy ?: 0.0f,
            max_life_expectancy = max_life_expectancy ?: 0.0f,
            max_height_male = max_height_male ?: 0.0f,
            max_height_female = max_height_female ?: 0.0f,
            max_weight_male = max_weight_male ?: 0.0f,
            max_weight_female = max_weight_female ?: 0.0f,
            min_height_male = min_height_male ?: 0.0f,
            min_height_female = min_height_female ?: 0.0f,
            min_weight_male = min_weight_male ?: 0.0f,
            min_weight_female = min_weight_female ?: 0.0f
        )
    }

    override suspend fun fetchAll(): List<DogDomain> {
        Log.d(TAG, "Fetching all dogs")
        return dao.fetchAllDogs().map {dog ->
            dog.toDogDomain()
        }
    }

    override suspend fun fetchByName(name: String): List<DogDomain> {
        Log.d(TAG, "Fetching dog by name $name")
        return dao.fetchDogsByName(name).map {dog ->
            dog.toDogDomain()
        }
    }

    override suspend fun fetchById(dogId: String): DogDomain {
        Log.d(TAG, "Fetching dog by id $dogId")
        return dao.fetchDogById(dogId).toDogDomain()
    }

    override suspend fun insert(dog: DogDomain) {
        Log.d(TAG, "Adding new Dog with name: ${dog.name}")
        dao.insert(dog.toDog())
    }

    companion object {
        private const val TAG = "DogRepository"
    }
}