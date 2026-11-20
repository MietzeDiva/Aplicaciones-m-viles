# WIM 100 - Sistema Digital de Postulación y Evaluación de Candidatas

Aplicación Android nativa en Kotlin, Jetpack Compose, Material 3, MVVM y Room.

## Integrantes

- Diego Saavedra
- Antonia Ávila

## API externa

Se integró Open-Meteo mediante Retrofit. La respuesta se muestra como tarjeta independiente en la pantalla de candidatas y no modifica los datos locales.

- Base URL: https://api.open-meteo.com/
- Endpoint: GET /v1/forecast
- Parámetros: latitude=-33.4489, longitude=-70.6693, current=temperature_2m,wind_speed_10m,weather_code, timezone=auto
- No requiere API key.

## Pruebas unitarias

La lógica de estados de `WeatherViewModel` se prueba con una implementación falsa de `WeatherRepository`, cubriendo respuesta exitosa y error de red.

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

La ejecución puede depender de la configuración local de Java/Gradle del equipo. El test está en `app/src/test/java/com/example/app_kotlin/WeatherViewModelTest.kt`.

## APK firmado

1. Generar una llave local (no subirla al repositorio):

```powershell
New-Item -ItemType Directory -Force keystore
keytool -genkeypair -v -keystore keystore/wim-release.jks -alias wim-release -keyalg RSA -keysize 2048 -validity 10000
Copy-Item signing.properties.example signing.properties
# Editar signing.properties con la contraseña elegida
.\gradlew.bat :app:assembleRelease
```

El APK queda en `app/build/outputs/apk/release/app-release.apk`. La llave y las contraseñas están excluidas por `.gitignore`.

## Ejecución

Abrir en Android Studio, sincronizar Gradle, conectar un dispositivo/emulador API 24+ y ejecutar `app`.
