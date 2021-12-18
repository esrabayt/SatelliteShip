package com.satelliteship.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class PositionWorker(context: Context, userParameters: WorkerParameters) :
    Worker(context, userParameters) {

    companion object {
        val KEY_SATELLITE_POSITION = "key.satellite.position"
    }

    override fun doWork(): Result {
        try {
            val outputData = Data.Builder()
                .putInt(KEY_SATELLITE_POSITION, getPosition())
                .build()

            return Result.success(outputData)
        } catch (e: Exception) {
            return Result.failure()
        }

    }

    private fun getPosition(): Int {
        val positionList = listOf(0, 1, 2)
        return positionList.random()
    }

}
