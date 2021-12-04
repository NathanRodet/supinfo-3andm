package com.supinfo.supfitness.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class WeightListModelFactory ( private val context : Context?) : ViewModelProvider. Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeightListModel::class.java)) {
            if (context == null) {
                throw Exception("No context to create repository ")
            }
            val repository = WeightRepository(context)
            return WeightListModel(repository) as T
        }
        throw IllegalArgumentException(" Unknown ViewModel class ")
    }
}