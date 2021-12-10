package com.supinfo.supfitness.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.supinfo.supfitness.database.data.Weight

@Dao
interface WeightDao {

    @Query("SELECT weight FROM Weight")
    fun getWeights(): MutableList<Float>

    @Query("SELECT date FROM Weight WHERE id=(SELECT MAX(id) FROM Weight)")
    fun getDates(): MutableList<String>

    @Query("SELECT weight FROM Weight")
    fun loadWeights(): LiveData<List<Float>>

    @Query (" SELECT * FROM Weight ")
    fun getList () : LiveData<List<Weight>>

    @Query("SELECT * FROM Weight")
    fun getAll(): List<Weight>

    @Query("SELECT * FROM Weight WHERE id IN (:weightIds)")
    fun loadAllByIds(weightIds: IntArray): List<Weight>

    @Query("SELECT * FROM Weight WHERE user LIKE :first")

    fun findByName(first: String): Weight

    @Insert
    fun insertAll(vararg weights: Weight)

    @Delete
    fun delete(weight: Weight)
}