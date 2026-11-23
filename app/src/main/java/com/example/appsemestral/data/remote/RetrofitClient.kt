package com.example.appsemestral.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Emulador de Android: 10.0.2.2 es tu PC.
    // Celular real: pon la IP de tu PC en el WiFi, ej. "192.168.1.50"
    private const val HOST = "10.0.2.2"

    private fun crear(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val weatherApi: WeatherApi by lazy {
        crear("https://api.open-meteo.com/").create(WeatherApi::class.java)
    }

    val candidataApi: CandidataApi by lazy {
        crear("http://$HOST:8081/").create(CandidataApi::class.java)
    }

    val evaluacionApi: EvaluacionApi by lazy {
        crear("http://$HOST:8082/").create(EvaluacionApi::class.java)
    }

    val historialApi: HistorialApi by lazy {
        crear("http://$HOST:8082/").create(HistorialApi::class.java)
    }
}
