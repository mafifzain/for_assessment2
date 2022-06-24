package org.d3if0060.assessment2.data

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0060.assessment2.network.TokoApi

class TokoViewModel(private val tokoDao: TokoDao): ViewModel() {
    fun getAllDataToko(): LiveData<List<TokoEntity>> = tokoDao.getDataToko()

    init {
        retriveImage()
    }

    private fun retriveImage(){
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = TokoApi.service.getImage()
                Log.d("TokoViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("TokoViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun addToko(tokoEntity: TokoEntity){
        viewModelScope.launch{
            tokoDao.insert(tokoEntity)
        }
    }

    fun deleteToko(tokoEntity: TokoEntity){
        viewModelScope.launch ( Dispatchers.IO ){
            tokoDao.delete(tokoEntity)
        }
    }
}

class TokoViewModelFactory(private val tokoDao: TokoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TokoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TokoViewModel(tokoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}