package com.supinfo.supfitness.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Track(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "user") var user: String,
    @ColumnInfo(name = "speed") var weight: Int,
    @ColumnInfo(name = "longitude") var longitude: Float,
    @ColumnInfo(name = "latitude") var latitude: Float,
    @ColumnInfo(name = "date") var date: String,
)