package com.satelliteship.data.datasource

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.satelliteship.domain.model.SatelliteDetail
import com.satelliteship.utils.GsonUtil
import com.satelliteship.utils.getJsonDataFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: GsonUtil
) {

    var satelliteDetailList: List<SatelliteDetail> = listOf()

    init {
        initializeData()
    }

    private fun initializeData() {
        if (satelliteDetailList.isNotEmpty()) return
        val jsonFileString = getJsonDataFromAsset(context, "satellite-detail.json").toString()
        val listType = object : TypeToken<List<SatelliteDetail>>() {}.type
        satelliteDetailList = gson.fromJson(jsonFileString, listType)
    }
}
