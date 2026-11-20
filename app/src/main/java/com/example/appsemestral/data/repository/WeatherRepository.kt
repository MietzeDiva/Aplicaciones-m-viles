package com.example.appsemestral.data.repository

import com.example.appsemestral.data.remote.CurrentWeather
import com.example.appsemestral.data.remote.WeatherApi

interface WeatherRepository { suspend fun currentWeather(): CurrentWeather }

class OpenMeteoWeatherRepository(
    private val api: WeatherApi,
    private val latitude: Double = -33.4489,
    private val longitude: Double = -70.6693
) : WeatherRepository {
    override suspend fun currentWeather(): CurrentWeather =
        api.currentWeather(latitude, longitude).current
}