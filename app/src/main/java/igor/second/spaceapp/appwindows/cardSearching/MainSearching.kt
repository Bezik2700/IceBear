package igor.second.spaceapp.appwindows.cardSearching

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import igor.second.spaceapp.R

@Composable
fun MainSearching(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var hasLocationPermission by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var targetLocation by remember { mutableStateOf<Location?>(null) }
    var isTargetReached by remember { mutableStateOf(false) }
    var distanceToTarget by remember { mutableDoubleStateOf(0.0) }
    var bearingToTarget by remember { mutableFloatStateOf(0f) }
    var compassDirection by remember { mutableFloatStateOf(0f) }
    var arrowBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(Unit) {
        arrowBitmap = createDirectionArrowBitmap()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> hasLocationPermission = isGranted }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            locationUpdate(
                locationClient = locationClient,
                onLocationUpdate = { location ->
                    userLocation = location
                    targetLocation?.let { target ->
                        distanceToTarget = location.distanceTo(target).toDouble()
                        bearingToTarget = location.bearingTo(target)
                        isTargetReached = distanceToTarget < 1
                    }
                },
                context = context
            )
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            if (targetLocation == null) {
                targetLocation = createTargetLocation(location, 100.0)
            }
        }
    }

    // compass
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
                Button(
                    onClick = {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                ) {
                    Text("Запросить разрешение")
                }
            }

            userLocation == null -> {
                CircularProgressIndicator()
                Text("Определяем ваше местоположение...")
            }

            isTargetReached -> {
                Text("Поздравляем!")
                Text("Вы достигли цели!")
            }

            else -> {

                val relativeBearing = (bearingToTarget - compassDirection + 360) % 360
                val arrowRotation by animateFloatAsState(targetValue = relativeBearing, animationSpec = tween(durationMillis = 200))

                Card (
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 32.dp, bottom = 64.dp)
                ) {
                    Box(modifier = modifier.fillMaxSize()){
                        Image(
                            painterResource(R.drawable.ic_launcher_background),
                            contentDescription = "map",
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Column (
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.fillMaxSize().padding(bottom = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(96.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    drawCircle(
                                        color = Color.Black.copy(alpha = 0.2f),
                                        radius = size.minDimension / 2
                                    )
                                }
                                arrowBitmap?.let {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .rotate(arrowRotation)
                                    )
                                }
                            }
                            Text("Дистанция до цели:")
                            Text(
                                text = "${"%.1f".format(distanceToTarget)} метров",
                                style = MaterialTheme.typography.displayMedium
                            )
                            Text(
                                text = "Направление: ${"%.0f".format(relativeBearing)}°",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        LinearProgressIndicator(
                            progress = { (1 - (distanceToTarget / 30)).toFloat() },
                            modifier = Modifier
                                .width(320.dp)
                                .height(4.dp)
                                .rotate(90f)
                                .align(Alignment.CenterEnd)
                                .offset(y = (-120).dp)
                        )
                    }
                }
            }
        }
    }
}
