package com.example.appsemestral.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appsemestral.model.Candidata
import com.example.appsemestral.ui.viewmodel.CandidataListViewModel
import com.example.appsemestral.ui.viewmodel.WeatherUiState
import com.example.appsemestral.ui.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidataListScreen(
    viewModel: CandidataListViewModel,
    weatherViewModel: WeatherViewModel,
    onAgregarClick: () -> Unit,
    onEditarClick: (Candidata) -> Unit
) {
    val candidatas by viewModel.candidatas.collectAsState()
    val weatherState by weatherViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Candidatas Registradas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            WeatherCard(weatherState, weatherViewModel::refresh)
            if (candidatas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay candidatas registradas.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(candidatas) { candidata ->
                    CandidataCard(
                        candidata = candidata,
                        onEliminar = { viewModel.eliminarCandidata(candidata) },
                        onEditar = { onEditarClick(candidata) }
                    )
                }
            }
        }
        }
    }
}

@Composable
private fun WeatherCard(state: WeatherUiState, onRefresh: () -> Unit) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        when (state) {
            WeatherUiState.Loading -> Text("Consultando clima de Santiago…", Modifier.padding(16.dp))
            is WeatherUiState.Success -> Column(Modifier.padding(16.dp)) {
                Text("Clima actual en Santiago", fontWeight = FontWeight.Bold)
                Text("${state.weather.temperature_2m} °C · viento ${state.weather.wind_speed_10m} km/h")
            }
            is WeatherUiState.Error -> Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Clima no disponible", Modifier.weight(1f))
                TextButton(onClick = onRefresh) { Text("Reintentar") }
            }
        }
    }
}

@Composable
fun CandidataCard(candidata: Candidata, onEliminar: () -> Unit, onEditar: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = candidata.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Área: ${candidata.area}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Experiencia: ${candidata.anosExperiencia} años",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = candidata.correo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row {
                IconButton(onClick = onEditar) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onEliminar) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}