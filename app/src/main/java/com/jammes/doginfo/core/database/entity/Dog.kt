package com.jammes.doginfo.core.database.entity

import androidx.room.Entity

@Entity(tableName = "dogs", primaryKeys = ["uuid"])
data class Dog(
    val uuid: String,
    val name: String,
    val good_with_children: Int? = null,
    val good_with_other_dogs: Int? = null,
    val shedding: Int? = null,
    val grooming: Int? = null,
    val drooling: Int? = null,
    val coat_length: Int? = null,
    val good_with_strangers: Int? = null,
    val playfulness: Int? = null,
    val protectiveness: Int? = null,
    val trainability: Int? = null,
    val energy: Int? = null,
    val barking: Int? = null,
    val min_life_expectancy: Float? = null,
    val max_life_expectancy: Float? = null,
    val max_height_male: Float? = null,
    val max_height_female: Float? = null,
    val max_weight_male: Float? = null,
    val max_weight_female: Float? = null,
    val min_height_male: Float? = null,
    val min_height_female: Float? = null,
    val min_weight_male: Float? = null,
    val min_weight_female: Float? = null,
    val image_dir: String,
    val image_link: String
)
