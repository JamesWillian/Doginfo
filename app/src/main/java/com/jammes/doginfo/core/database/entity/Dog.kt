package com.jammes.doginfo.core.database.entity

import androidx.room.Entity

@Entity(tableName = "dogs", primaryKeys = ["uuid"])
data class Dog(
    val uuid: String,
    val name: String,
    val good_with_children: Int,
    val good_with_other_dogs: Int,
    val shedding: Int,
    val grooming: Int,
    val drooling: Int,
    val coat_length: Int,
    val good_with_strangers: Int,
    val playfulness: Int,
    val protectiveness: Int,
    val trainability: Int,
    val energy: Int,
    val barking: Int,
    val min_life_expectancy: Float,
    val max_life_expectancy: Float,
    val max_height_male: Float,
    val max_height_female: Float,
    val max_weight_male: Float,
    val max_weight_female: Float,
    val min_height_male: Float,
    val min_height_female: Float,
    val min_weight_male: Float,
    val min_weight_female: Float,
    val image_dir: String
)
