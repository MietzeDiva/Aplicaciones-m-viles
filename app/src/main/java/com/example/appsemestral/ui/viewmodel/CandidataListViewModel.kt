package com.example.appsemestral.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsemestral.data.repository.CandidataRepository
import com.example.appsemestral.model.Candidata
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CandidataListViewModel(
    private val repository: CandidataRepository
) : ViewModel() {

    val candidatas: StateFlow<List<Candidata>> = repository.listarCandidatas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        // Sincroniza con el backend cada 5 segundos
        viewModelScope.launch {
            while (true) {
                repository.refrescarCandidatas()
                delay(5000)
            }
        }
    }

    fun eliminarCandidata(candidata: Candidata) {
        viewModelScope.launch {
            try {
                repository.eliminarCandidata(candidata)
            } catch (e: Exception) {
                Log.e("WimList", "No se pudo eliminar: ${e.message}", e)
            }
        }
    }
}
