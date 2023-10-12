package com.jammes.doginfo.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jammes.doginfo.core.database.entity.Dog

@Dao
interface DogDao {

    @Query("SELECT uuid, name, image_dir, image_link FROM dogs")
    suspend fun fetchAllDogs(): List<Dog>

    @Query("SELECT uuid, name, image_dir, image_link FROM dogs WHERE name LIKE '%'||:name||'%'")
    suspend fun fetchDogsByName(name: String): List<Dog>

    @Query("SELECT * FROM dogs WHERE uuid = :dogId")
    suspend fun fetchDogById(dogId: String): Dog

    @Query("SELECT true FROM dogs WHERE name = :name")
    suspend fun dogExists(name: String): Boolean?

    @Insert
    suspend fun insert(dog: Dog)

}