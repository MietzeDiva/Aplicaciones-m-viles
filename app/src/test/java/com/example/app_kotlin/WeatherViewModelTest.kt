package com.example.app_kotlin

import com.example.appsemestral.data.remote.CurrentWeather
import com.example.appsemestral.data.repository.WeatherRepository
import com.example.appsemestral.ui.viewmodel.WeatherUiState
import com.example.appsemestral.ui.viewmodel.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    @Before fun setUp() = Dispatchers.setMain(dispatcher)
    @After fun tearDown() = Dispatchers.resetMain()

    @Test fun successfulResponseIsExposed() = runTest {
        val expected = CurrentWeather(22.5, 12.0, 1)
        val viewModel = WeatherViewModel(object : WeatherRepository {
            override suspend fun currentWeather() = expected
        })
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(WeatherUiState.Success(expected), viewModel.state.value)
    }

    @Test fun repositoryFailureIsExposed() = runTest {
        val viewModel = WeatherViewModel(object : WeatherRepository {
            override suspend fun currentWeather(): CurrentWeather = error("sin conexión")
        })
        dispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.state.value is WeatherUiState.Error)
        assertEquals("sin conexión", (viewModel.state.value as WeatherUiState.Error).message)
    }
}