package org.d3if0060.assessment2.data

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0060.assessment2.network.ApiStatus
import org.d3if0060.assessment2.network.TokoApi

class TokoViewModel(private val tokoDao: TokoDao): ViewModel() {
    private val data = MutableLiveData<List<TokoImage>>()


    init {

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

    fun getData(): LiveData<List<TokoImage>> = data

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