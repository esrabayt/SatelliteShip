package com.satelliteship.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.satelliteship.data.datasource.SatelliteDataSource
import com.satelliteship.domain.model.Satellite
import javax.inject.Inject

class FetchSatelliteUseCase @Inject constructor(
    private val satelliteDataSource: SatelliteDataSource
) {

    private var satelliteList = listOf<Satellite>()
    private val satelliteItems = MutableLiveData<List<Satellite>>()

    operator fun invoke() {
        satelliteList = satelliteDataSource.getSatellites()
    }

    fun observe(searchText: String): LiveData<List<Satellite>> {
        satelliteItems.value = satelliteList.filter { it.name.contains(searchText, true) }
        return satelliteItems
    }
}
