package com.supinfo.supfitness.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weight(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "user") var user: String,
    @ColumnInfo(name = "weight") var weight: Float,
    @ColumnInfo(name = "date") var date: String,
)



