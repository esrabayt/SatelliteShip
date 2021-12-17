package com.satelliteship.domain.usecase

import com.satelliteship.data.datasource.DetailDataSource
import com.satelliteship.domain.model.SatelliteDetail
import javax.inject.Inject

class FetchDetailUseCase @Inject constructor(
    private var detailDataSource: DetailDataSource
) {
    operator fun invoke(): List<SatelliteDetail> {
        return detailDataSource.satelliteDetailList
    }
}
