package com.supinfo.supfitness.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.supinfo.supfitness.database.dao.TrackDao
import com.supinfo.supfitness.database.dao.WeightDao
import com.supinfo.supfitness.database.data.Track
import com.supinfo.supfitness.database.data.Weight


// Defining a class to hold the database and configuration for persistent data.
// 'entities' must define all the associated data entities associated to the database.
@Database(entities = [Weight::class, Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWeightDao(): WeightDao
    abstract fun getTrackDao(): TrackDao

    // Share the db code for instance
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "database.db")
                        .allowMainThreadQueries()
                        .build()
                    return INSTANCE!!
                }
            } else {
                return INSTANCE!!
            }
        }

    }

}