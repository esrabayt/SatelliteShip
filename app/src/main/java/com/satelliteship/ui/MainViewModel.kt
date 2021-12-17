package com.satelliteship.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.satelliteship.core.BaseViewModel
import com.satelliteship.domain.model.Position
import com.satelliteship.domain.model.Positions
import com.satelliteship.domain.model.Satellite
import com.satelliteship.domain.model.SatelliteDetail
import com.satelliteship.domain.usecase.FetchDetailUseCase
import com.satelliteship.domain.usecase.FetchPositionsUseCase
import com.satelliteship.domain.usecase.FetchSatelliteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val fetchSatelliteUseCase: FetchSatelliteUseCase,
    val fetchDetailUseCase: FetchDetailUseCase,
    val fetchPositionsUseCase: FetchPositionsUseCase
) : BaseViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _items: LiveData<List<Satellite>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                fetchSatelliteUseCase()
                _dataLoading.value = false
            }
        }
        fetchSatelliteUseCase.observe()
    }

    val items: LiveData<List<Satellite>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val detailList: List<SatelliteDetail>
        get() = fetchDetailUseCase()

    private val positionList: List<Positions>
        get() = fetchPositionsUseCase()

    init {
        loadSatellites(true)
    }

    private fun loadSatellites(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    fun getDetail(satellite: Satellite): SatelliteDetail? {
        return detailList.firstOrNull { it.id == satellite.id }
    }

    fun getPositions(id: Int): Position? {
        return positionList.firstOrNull() { it.id == id }?.positions?.get(0)
    }

}
