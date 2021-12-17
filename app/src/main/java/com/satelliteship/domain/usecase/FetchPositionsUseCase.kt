package com.satelliteship.domain.usecase

import com.satelliteship.data.datasource.PositionsDataSource
import com.satelliteship.domain.model.Positions
import javax.inject.Inject

class FetchPositionsUseCase @Inject constructor(
    private var positionsDataSource: PositionsDataSource
) {
    operator fun invoke(): List<Positions> {
        return positionsDataSource.positionResponse?.list.orEmpty()
    }
}
