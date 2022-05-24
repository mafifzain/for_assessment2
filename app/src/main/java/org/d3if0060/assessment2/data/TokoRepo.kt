package org.d3if0060.assessment2.data

import androidx.lifecycle.LiveData

class TokoRepo(private val tokoDao: TokoDao) {
    val getAllDataToko: LiveData<List<TokoEntity>> = tokoDao.getDataToko()

    suspend fun addToko(tokoEntity: TokoEntity){
        tokoDao.insert(tokoEntity)
    }

    suspend fun deleteToko(tokoEntity: TokoEntity){
        tokoDao.delete(tokoEntity)
    }
}