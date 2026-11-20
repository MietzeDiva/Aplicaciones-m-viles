package com.example.appsemestral.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(val current: CurrentWeather)
data class CurrentWeather(val temperature_2m: Double, val wind_speed_10m: Double, val weather_code: Int)

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun currentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,wind_speed_10m,weather_code",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}