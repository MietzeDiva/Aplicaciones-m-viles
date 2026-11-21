package com.example.appsemestral.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appsemestral.ui.viewmodel.WeatherUiState

@Composable
fun WeatherCard(
    state: WeatherUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        when (state) {
            WeatherUiState.Loading -> Text(
                text = "Cargando clima de Santiago...",
                modifier = Modifier.padding(16.dp)
            )
            is WeatherUiState.Success -> Column(Modifier.padding(16.dp)) {
                Text("Clima actual en Santiago")
                Text("Temperatura: " + state.weather.temperature_2m + " °C")
                Text("Viento: " + state.weather.wind_speed_10m + " km/h")
            }
            is WeatherUiState.Error -> Row(Modifier.padding(16.dp)) {
                Text("Error: " + state.message, Modifier.weight(1f))
                TextButton(onClick = onRetry) { Text("Reintentar") }
            }
        }
    }
}
