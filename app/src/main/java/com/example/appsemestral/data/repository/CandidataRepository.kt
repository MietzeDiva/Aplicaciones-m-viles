package com.example.appsemestral.data.repository

import android.util.Log
import com.example.appsemestral.data.remote.CandidataApi
import com.example.appsemestral.data.remote.EvaluacionApi
import com.example.appsemestral.data.remote.HistorialApi
import com.example.appsemestral.model.Candidata
import com.example.appsemestral.model.Evaluacion
import com.example.appsemestral.model.HistorialAccion
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CandidataRepository(
    private val candidataApi: CandidataApi,
    private val evaluacionApi: EvaluacionApi,
    private val historialApi: HistorialApi
) {

    private val _candidatas = MutableStateFlow<List<Candidata>>(emptyList())
    private val _ranking = MutableStateFlow<List<Evaluacion>>(emptyList())
    private val _historial = MutableStateFlow<List<HistorialAccion>>(emptyList())

    private fun fechaActual(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    // Ejecuta una llamada de red sin que la app se caiga si el servidor falla
    private suspend fun <T> intentar(porDefecto: T, bloque: suspend () -> T): T =
        try {
            bloque()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("WimRepo", "Error de red: ${e.message}", e)
            porDefecto
        }

    // --- Refrescar datos desde el backend ---
    suspend fun refrescarCandidatas() {
        intentar(Unit) { _candidatas.value = candidataApi.listar() }
    }

    suspend fun refrescarRanking() {
        intentar(Unit) { _ranking.value = evaluacionApi.ranking() }
    }

    suspend fun refrescarHistorial() {
        intentar(Unit) { _historial.value = historialApi.listar() }
    }

    suspend fun refrescarTodo() {
        refrescarCandidatas()
        refrescarRanking()
        refrescarHistorial()
    }

    private suspend fun registrarHistorial(accion: String, descripcion: String) {
        intentar(Unit) {
            historialApi.crear(HistorialAccion(accion = accion, descripcion = descripcion, fecha = fechaActual()))
            refrescarHistorial()
        }
    }

    // --- Candidatas (CRUD contra el backend) ---
    fun listarCandidatas(): Flow<List<Candidata>> = _candidatas.asStateFlow()

    suspend fun buscarCandidata(id: Int): Candidata? = intentar(null) { candidataApi.buscar(id) }

    suspend fun registrarCandidata(candidata: Candidata): Long {
        val creada = candidataApi.crear(candidata)
        registrarHistorial(
            "Registro de Candidata",
            "Se registró a la candidata ${candidata.nombre} en el área ${candidata.area}"
        )
        refrescarCandidatas()
        return creada.id.toLong()
    }

    suspend fun actualizarCandidata(candidata: Candidata) {
        candidataApi.actualizar(candidata.id, candidata)
        registrarHistorial("Edición de Candidata", "Se actualizaron los datos de ${candidata.nombre}")
        refrescarCandidatas()
    }

    suspend fun eliminarCandidata(candidata: Candidata) {
        candidataApi.eliminar(candidata.id)
        registrarHistorial("Eliminación", "Se eliminó a la candidata ${candidata.nombre}")
        refrescarCandidatas()
    }

    // --- Evaluaciones WIM ---
    fun obtenerRanking(): Flow<List<Evaluacion>> = _ranking.asStateFlow()

    suspend fun buscarEvaluacionPorCandidata(candidataId: Int): Evaluacion? =
        intentar(null) {
            val respuesta = evaluacionApi.porCandidata(candidataId)
            if (respuesta.isSuccessful) respuesta.body() else null
        }

    suspend fun guardarEvaluacion(evaluacion: Evaluacion): Long {
        val creada = evaluacionApi.crear(evaluacion)
        registrarHistorial(
            "Evaluación WIM",
            "Se evaluó a ${evaluacion.candidataNombre} con puntaje total ${String.format(Locale.US, "%.2f", evaluacion.totalPonderado)}"
        )
        refrescarRanking()
        return creada.id.toLong()
    }

    // --- Historial ---
    fun listarHistorial(): Flow<List<HistorialAccion>> = _historial.asStateFlow()
}
