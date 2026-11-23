package com.example.appsemestral.data.remote

import com.example.appsemestral.model.Candidata
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CandidataApi {
    @GET("api/candidatas")
    suspend fun listar(): List<Candidata>

    @GET("api/candidatas/{id}")
    suspend fun buscar(@Path("id") id: Int): Candidata

    @POST("api/candidatas")
    suspend fun crear(@Body candidata: Candidata): Candidata

    @PUT("api/candidatas/{id}")
    suspend fun actualizar(@Path("id") id: Int, @Body candidata: Candidata): Candidata

    @DELETE("api/candidatas/{id}")
    suspend fun eliminar(@Path("id") id: Int)
}
