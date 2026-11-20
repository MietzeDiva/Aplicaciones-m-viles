package com.example.appsemestral.di

import android.content.Context
import com.example.appsemestral.data.local.WimDatabase
import com.example.appsemestral.data.remote.WeatherApi
import com.example.appsemestral.data.repository.CandidataRepository
import com.example.appsemestral.data.repository.OpenMeteoWeatherRepository
import com.example.appsemestral.data.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(private val context: Context) {
    val database by lazy { WimDatabase.obtener(context) }
    val candidataRepository by lazy {
        CandidataRepository(database.candidataDao(), database.evaluacionDao(), database.historialDao())
    }
    private val weatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
    val weatherRepository: WeatherRepository by lazy { OpenMeteoWeatherRepository(weatherApi) }
}