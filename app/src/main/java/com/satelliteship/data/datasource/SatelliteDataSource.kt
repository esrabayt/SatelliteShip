package com.satelliteship.data.datasource

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.satelliteship.domain.model.Satellite
import com.satelliteship.utils.GsonUtil
import com.satelliteship.utils.getJsonDataFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: GsonUtil
) {
    private var satelliteList: List<Satellite> = listOf()

    init {
        initializeData()
    }

    fun getSatellites() = satelliteList

    private fun initializeData() {
        if (satelliteList.isNotEmpty()) return
        val jsonFileString = getJsonDataFromAsset(context, "satellite-list.json").toString()
        val listType = object : TypeToken<List<Satellite>>() {}.type
        satelliteList = gson.fromJson(jsonFileString, listType)
    }

}
