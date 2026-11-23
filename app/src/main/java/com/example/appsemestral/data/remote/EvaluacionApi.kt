package com.example.appsemestral.data.remote

import com.example.appsemestral.model.Evaluacion
import com.example.appsemestral.model.HistorialAccion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EvaluacionApi {
    @GET("api/evaluaciones/ranking")
    suspend fun ranking(): List<Evaluacion>

    // Response<> para manejar el 404 (candidata sin evaluación) sin que explote
    @GET("api/evaluaciones/candidata/{id}")
    suspend fun porCandidata(@Path("id") id: Int): Response<Evaluacion>

    @POST("api/evaluaciones")
    suspend fun crear(@Body evaluacion: Evaluacion): Evaluacion
}

interface HistorialApi {
    @GET("api/historial")
    suspend fun listar(): List<HistorialAccion>

    @POST("api/historial")
    suspend fun crear(@Body historial: HistorialAccion): HistorialAccion
}
