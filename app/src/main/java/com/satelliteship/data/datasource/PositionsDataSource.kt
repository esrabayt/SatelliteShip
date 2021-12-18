package com.satelliteship.data.datasource

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.satelliteship.domain.model.PositionResponse
import com.satelliteship.utils.GsonUtil
import com.satelliteship.utils.getJsonDataFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PositionsDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: GsonUtil
) {
    var positionResponse: PositionResponse? = null

    init {
        initializeData()
    }

    private fun initializeData() {
        if (positionResponse != null) return
        val jsonFileString = getJsonDataFromAsset(context, "positions.json").toString()
        val listType = object : TypeToken<PositionResponse>() {}.type
        positionResponse = gson.fromJson(jsonFileString, listType)
    }
}
