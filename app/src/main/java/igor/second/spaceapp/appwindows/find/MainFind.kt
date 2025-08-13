package igor.second.spaceapp.appwindows.find

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices

@Composable
fun MainFind() {

    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Состояния
    var hasLocationPermission by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var targetLocation by remember { mutableStateOf<Location?>(null) }
    var distanceToTarget by remember { mutableDoubleStateOf(0.0) }
    var bearingToTarget by remember { mutableFloatStateOf(0f) } // Азимут к цели
    var compassDirection by remember { mutableFloatStateOf(0f) } // Направление компаса
    var isTargetReached by remember { mutableStateOf(false) }

    // Bitmap для стрелки направления
    var arrowBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Инициализация стрелки
    LaunchedEffect(Unit) {
        arrowBitmap = createDirectionArrowBitmap(context)
    }

    // Запрос разрешения на геолокацию
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
    }

    // Запуск при старте
    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // Обновление локации
    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            startLocationUpdates(
                locationClient = locationClient,
                onLocationUpdate = { location ->
                    userLocation = location
                    targetLocation?.let { target ->
                        // Расчет расстояния и направления
                        distanceToTarget = location.distanceTo(target).toDouble()
                        bearingToTarget = location.bearingTo(target)
                        isTargetReached = distanceToTarget < 10
                    }
                },
                context = context
            )
        }
    }

    // Создание цели при первом получении локации
    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            if (targetLocation == null) {
                targetLocation = createTargetLocation(location, 100.0)
            }
        }
    }

    // Датчик компаса
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                    val rotationMatrix = FloatArray(9)
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, orientation)
                    compassDirection = Math.toDegrees(orientation[0].toDouble()).toFloat()
                }
            }
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        if (rotationSensor != null) {
            sensorManager.registerListener(
                sensorListener,
                rotationSensor,
                SensorManager.SENSOR_DELAY_GAME
            )
        }

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    // Интерфейс
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            !hasLocationPermission -> {
                Text("Требуется разрешение на доступ к геолокации")
                Button(onClick = { permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Text("Запросить разрешение")
                }
            }
            userLocation == null -> {
                CircularProgressIndicator()
                Text("Определяем ваше местоположение...")
            }
            isTargetReached -> {
                Text("Поздравляем!", style = MaterialTheme.typography.headlineMedium)
                Text("Вы достигли цели!")
            }
            else -> {
                // Относительное направление к цели с учетом компаса
                val relativeBearing = (bearingToTarget - compassDirection + 360) % 360
                val arrowRotation by animateFloatAsState(
                    targetValue = relativeBearing,
                    animationSpec = tween(durationMillis = 200)
                )

                // Компас с направлением
                Box(
                    modifier = Modifier.size(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Фон компаса
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.Black.copy(alpha = 0.2f),
                            radius = size.minDimension / 2
                        )
                    }

                    // Стрелка направления
                    arrowBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Direction arrow",
                            modifier = Modifier
                                .size(150.dp)
                                .rotate(arrowRotation)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Информация
                Text("Дистанция до цели:", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "${"%.1f".format(distanceToTarget)} метров",
                    style = MaterialTheme.typography.displayMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Направление: ${"%.0f".format(relativeBearing)}°",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = (1 - (distanceToTarget / 100)).toFloat(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}