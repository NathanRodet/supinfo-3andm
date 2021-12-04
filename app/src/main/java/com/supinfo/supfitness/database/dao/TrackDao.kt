package com.supinfo.supfitness.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.supinfo.supfitness.database.data.Track

@Dao
interface TrackDao {
    @Query("SELECT * FROM track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM track WHERE id IN (:trackIds)")
    fun loadAllByIds(trackIds: IntArray): List<Track>

    @Query("SELECT * FROM track WHERE user LIKE :first")
    fun findByName(first: String): Track

    @Insert
    fun insertAll(vararg tracks: Track)

    @Delete
    fun delete(track: Track)
}