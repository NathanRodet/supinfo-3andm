package com.supinfo.supfitness.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.supinfo.supfitness.database.data.Weight

@Dao
interface WeightDao {

    // Used in WeightActivity, ChartActivity
    @Query("SELECT weight FROM Weight")
    fun getWeights(): MutableList<Float>
    // Used in WeightActivity
    @Query("SELECT date FROM Weight WHERE id=(SELECT MAX(id) FROM Weight)")
    fun getDates(): MutableList<String>

    @Query("SELECT weight FROM Weight")
    fun loadWeights(): LiveData<List<Float>>

    @Query (" SELECT * FROM Weight ")
    fun getList () : LiveData<List<Weight>>

    @Query("SELECT * FROM Weight WHERE id IN (:weightIds)")
    fun loadAllByIds(weightIds: IntArray): List<Weight>

    @Query("SELECT * FROM Weight WHERE user LIKE :first")
    fun findByName(first: String): Weight

    @Query("SELECT * FROM Weight")
    fun getAll(): List<Weight>

    @Query("SELECT * FROM Weight")
    fun getAllLiveData(): LiveData<List<Weight>>

    // Used in WeightActivity
    @Insert
    fun insertAll(vararg weights: Weight)
    // Used in WeightActivity
    @Delete
    fun delete(weight: Weight)

    @Update
    fun update(weight: Weight)
}