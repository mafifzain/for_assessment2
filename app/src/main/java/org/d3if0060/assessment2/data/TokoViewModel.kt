package org.d3if0060.assessment2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TokoViewModel(application: Application): AndroidViewModel(application) {
    val getAllDataToko: LiveData<List<TokoEntity>>
    private val repo: TokoRepo

    init {
        val tokoDao = TokoDb.getDataDb(application).tokoDao()
        repo = TokoRepo(tokoDao)
        getAllDataToko = repo.getAllDataToko
    }

    fun addToko(tokoEntity: TokoEntity){
        viewModelScope.launch (Dispatchers.IO){
            repo.addToko(tokoEntity)
        }
    }

    fun deleteToko(tokoEntity: TokoEntity){
        viewModelScope.launch ( Dispatchers.IO ){
            repo.deleteToko(tokoEntity)
        }
    }
}