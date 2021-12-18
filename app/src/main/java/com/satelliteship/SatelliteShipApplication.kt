package com.satelliteship

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class SatelliteShipApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
