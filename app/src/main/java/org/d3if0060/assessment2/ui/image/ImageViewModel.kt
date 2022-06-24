package org.d3if0060.assessment2.ui.image

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0060.assessment2.data.TokoImage
import org.d3if0060.assessment2.network.ApiStatus
import org.d3if0060.assessment2.network.TokoApi

class ImageViewModel: ViewModel() {
    private val data = MutableLiveData<List<TokoImage>>()
    private val status = MutableLiveData<ApiStatus>()


    init {
        retriveImage()
    }

    private fun retriveImage(){
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(TokoApi.service.getImage())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("TokoViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }



    fun getData(): LiveData<List<TokoImage>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}