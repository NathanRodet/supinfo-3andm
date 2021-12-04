package com.supinfo.supfitness.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.supinfo.supfitness.database.data.Weight
import com.supinfo.supfitness.database.WeightRepository

class WeightListModel ( repository: WeightRepository): ViewModel() {
    val weightList : LiveData<List<Weight>> = repository.weightList
}
