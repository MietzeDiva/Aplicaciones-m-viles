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


## Entrega de API, pruebas y APK firmado

### Endpoint de clima

La aplicación utiliza Open-Meteo mediante Retrofit:

`GET https://api.open-meteo.com/v1/forecast`

Parámetros usados para Santiago:

`latitude=-33.4489`
`longitude=-70.6693`
`current=temperature_2m,wind_speed_10m,weather_code`
`timezone=auto`

La API es pública y no requiere API Key.

### Pruebas unitarias

Las pruebas de `CandidataValidator` se encuentran en:

`app/src/test/java/com/example/app_kotlin/CandidataValidatorTest.kt`

Incluyen casos válidos y errores para nombres, correos y teléfonos.

Ejecutar:

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

### Compilar y ejecutar en un emulador

1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Abrir Device Manager y crear un emulador con API 24 o superior.
4. Iniciar el emulador.
5. Seleccionar el módulo `app`.
6. Presionar **Run**.
7. Elegir el emulador y esperar la instalación.

También se puede compilar desde PowerShell:

```powershell
.\gradlew.bat :app:assembleDebug
```

### APK y llave de firma

La llave de firma se encuentra en:

`keystore/wim-release.jks`

La configuración de firma usa:

`signing.properties`

Las contraseñas y la llave están excluidas mediante `.gitignore`.

Generar la APK release firmada:

```powershell
.\gradlew.bat :app:assembleRelease
```

La APK firmada queda disponible en:

`app/build/outputs/apk/release/app-release.apk`
