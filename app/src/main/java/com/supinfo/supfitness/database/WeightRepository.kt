package com.supinfo.supfitness.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.supinfo.supfitness.database.AppDatabase
import com.supinfo.supfitness.database.data.Weight

class WeightRepository(context: Context) {
    private val getWeightDao = AppDatabase.getInstance(context).getWeightDao()
    val weightList: LiveData<List<Weight>> = getWeightDao.getList()
}