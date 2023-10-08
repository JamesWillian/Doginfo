package com.jammes.doginfo.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammes.doginfo.core.database.dao.DogDao
import com.jammes.doginfo.core.database.entity.Dog

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (instance==null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .build()
                }
            }

            return instance!!
        }

        private const val DATABASE_NAME = "doginfo-database.db"
    }
}