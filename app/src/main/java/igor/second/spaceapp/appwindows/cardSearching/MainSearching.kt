package igor.second.spaceapp.appwindows.cardSearching

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardSearching.locationCard.SearchingScreen
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.createDirectionArrowBitmap
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MainSearching(
    navController: NavController,
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel = viewModel()
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
    var distanceMeters by remember { mutableDoubleStateOf(((10..150).random()).toDouble()) }

    LaunchedEffect(Unit) { arrowBitmap = createDirectionArrowBitmap() }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> hasLocationPermission = isGranted }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // Запускаем обновление локации при получении разрешения
    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            locationViewModel.startLocationUpdates(
                locationClient = locationClient,
                onLocationUpdate = { location ->
                    userLocation = location
                    targetLocation?.let { target ->
                        distanceToTarget = location.distanceTo(target).toDouble()
                        bearingToTarget = location.bearingTo(target)
                        isTargetReached = distanceToTarget < 2
                    }
                },
                context = context
            )
        }
    }

    // Создаем целевую локацию при получении пользовательской локации
    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            if (targetLocation == null) {
                targetLocation = locationViewModel.createTargetLocation(location, distanceMeters)
            }
        }
    }

    // Останавливаем обновления при достижении цели
    LaunchedEffect(isTargetReached) {
        if (isTargetReached) {
            locationViewModel.stopLocationUpdates(locationClient)
        }
    }

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        when {
            !hasLocationPermission -> {
                Text("Требуется разрешение на доступ к геолокации")
                Button(
                    onClick = {
                        locationViewModel.openAppSettings(context = context)
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
                SearchingScreen(
                    distanceMeters = distanceMeters,
                    distanceToTarget = distanceMeters,
                    arrowBitmap = arrowBitmap,
                    bearingToTarget = bearingToTarget,
                    compassDirection = compassDirection,
                )
            }
        }
    }
    BackHandler {
        locationViewModel.stopLocationUpdates(locationClient)
        navController.navigate(Screens.MainIncome.route)
    }
}

class LocationViewModel : ViewModel() {

    private var locationCallback: LocationCallback? = null

    fun openAppSettings(context: Context) {
        viewModelScope.launch {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent)
        }
    }

    fun createTargetLocation(baseLocation: Location, distanceMeters: Double): Location {
        return Location("target").apply {
            // Более точное создание целевой точки
            val bearing = Math.random() * 360.0
            val distanceInDegrees = distanceMeters / 112800.0 // 1 градус ≈ 111 км

            latitude = baseLocation.latitude + (distanceInDegrees * cos(Math.toRadians(bearing)))
            longitude = baseLocation.longitude + (distanceInDegrees * sin(Math.toRadians(bearing)))
        }
    }

    fun startLocationUpdates(
        locationClient: FusedLocationProviderClient,
        onLocationUpdate: (Location) -> Unit,
        context: Context
    ) {
        // Сначала останавливаем предыдущие обновления
        stopLocationUpdates(locationClient)

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).apply { setMinUpdateIntervalMillis(500) }
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.lastOrNull()?.let(onLocationUpdate)
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationClient.requestLocationUpdates(
                locationRequest,
                locationCallback!!,
                null
            )
        }
    }

    fun stopLocationUpdates(locationClient: FusedLocationProviderClient) {
        locationCallback?.let {
            locationClient.removeLocationUpdates(it)
            locationCallback = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        locationCallback = null
    }
}