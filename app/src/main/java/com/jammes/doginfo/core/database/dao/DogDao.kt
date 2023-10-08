package com.jammes.doginfo.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jammes.doginfo.core.database.entity.Dog

@Dao
interface DogDao {

    @Query("SELECT uuid, name, image_dir FROM dogs")
    suspend fun fetchAllDogs(): List<Dog>

    @Query("SELECT uuid, name, image_dir FROM dogs WHERE name LIKE '%'||:name||'%'")
    suspend fun fetchDogsByName(dogName: String): List<Dog>

    @Query("SELECT * FROM dogs WHERE uuid = :uuid")
    suspend fun fetchDogById(dogId: String): Dog

    @Insert
    suspend fun insert(dog: Dog)
}