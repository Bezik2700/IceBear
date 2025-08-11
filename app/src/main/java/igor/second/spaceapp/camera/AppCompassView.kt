package igor.second.spaceapp.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

@Composable
fun ARCompassView(targetLocation: Location) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Состояния
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var distance by remember { mutableStateOf(0f) }
    var bearingToTarget by remember { mutableStateOf(0f) }
    var deviceAzimuth by remember { mutableStateOf(0f) }
    var gpsEnabled by remember { mutableStateOf(false) }
    var showGpsDialog by remember { mutableStateOf(false) }

    // Клиенты сервисов
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val locationManager = remember { context.getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    // Проверка включенного GPS
    LaunchedEffect(Unit) {
        gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gpsEnabled) {
            showGpsDialog = true
        }
    }

    // Диалог включения GPS
    if (showGpsDialog) {
        AlertDialog(
            onDismissRequest = { showGpsDialog = false },
            title = { Text("Включите GPS") },
            text = { Text("Для точного определения местоположения необходимо включить GPS") },
            confirmButton = {
                Button(onClick = {
                    context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    showGpsDialog = false
                }) {
                    Text("Перейти в настройки")
                }
            },
            dismissButton = {
                TextButton(onClick = { showGpsDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }

    // Компас (датчики устройства)
    val sensorListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                    val rotationMatrix = FloatArray(9)
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, orientation)
                    deviceAzimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }
    }

    DisposableEffect(Unit) {
        val rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
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

    // Получение местоположения с обновлениями
    DisposableEffect(gpsEnabled) {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000
        ).apply {
            setMinUpdateIntervalMillis(500)
            setWaitForAccurateLocation(true)
        }.build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.lastOrNull()?.let { location ->
                    if (location.accuracy < 50) {
                        userLocation = location
                        distance = location.distanceTo(targetLocation)
                        bearingToTarget = location.bearingTo(targetLocation)
                    }
                }
            }
        }

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && gpsEnabled
        ) {
            locationClient.requestLocationUpdates(
                locationRequest,
                ContextCompat.getMainExecutor(context),
                callback
            )
        }

        onDispose {
            locationClient.removeLocationUpdates(callback)
        }
    }

    // Быстрое получение последнего местоположения
    LaunchedEffect(Unit) {
        try {
            val lastLocation = withTimeoutOrNull(2000) {
                locationClient.lastLocation.await()
            }
            lastLocation?.let { userLocation = it }
        } catch (e: Exception) {
            Log.e("Location", "Error getting last location", e)
        }
    }

    // Анимация расстояния
    val animatedDistance by animateFloatAsState(
        targetValue = distance,
        animationSpec = tween(durationMillis = 1000)
    )

    // Угол поворота стрелки
    val arrowRotation by animateFloatAsState(
        targetValue = bearingToTarget - deviceAzimuth,
        animationSpec = tween(durationMillis = 200)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Камера
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { previewView ->
                val executor = ContextCompat.getMainExecutor(context)
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview
                        )
                    } catch(exc: Exception) {
                        Log.e("Camera", "Use case binding failed", exc)
                    }
                }, executor)
            }
        )

        // Компас с направлением к цели
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(150.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Фон компаса
                drawCircle(
                    color = Color.Black.copy(alpha = 0.5f),
                    radius = size.minDimension / 2
                )

                // Стрелка направления
                rotate(arrowRotation) {
                    drawLine(
                        color = Color.Red,
                        start = Offset(size.width / 2, size.height / 2),
                        end = Offset(size.width / 2, 0f),
                        strokeWidth = 4f
                    )

                    // Треугольник на конце стрелки
                    val path = Path().apply {
                        moveTo(size.width / 2 - 10, 20f)
                        lineTo(size.width / 2 + 10, 20f)
                        lineTo(size.width / 2, 0f)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = Color.Red
                    )
                }

                // Центральная точка
                drawCircle(
                    color = Color.White,
                    radius = 5f,
                    center = Offset(size.width / 2, size.height / 2)
                )
            }
        }

        // Дистанция до цели с анимацией
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            Text(
                text = "Дистанция: ${"%.1f".format(animatedDistance)} м",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        // Индикатор точности
        userLocation?.let { location ->
            if (location.accuracy > 20) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Точность: ±${"%.0f".format(location.accuracy)} м",
                        color = Color.Yellow
                    )
                }
            }
        }
    }
}