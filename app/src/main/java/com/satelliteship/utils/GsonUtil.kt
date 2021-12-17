package com.satelliteship.utils

import com.google.gson.Gson
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GsonUtil @Inject constructor() {
    private val gson = Gson()

    fun toJson(s: Any): String = gson.toJson(s)

    fun <T> fromJson(json: String, clazz: Class<T>): T = gson.fromJson<T>(json, clazz)

    fun <T> fromJson(json: String, type: Type): T = gson.fromJson(json, type)
}
