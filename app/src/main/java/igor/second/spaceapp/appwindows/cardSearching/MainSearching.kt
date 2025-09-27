package igor.second.spaceapp.appwindows.cardSearching

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardSearching.canvas.createDirectionArrowBitmap
import igor.second.spaceapp.appwindows.cardSearching.locationCard.OnSearchUser
import igor.second.spaceapp.appwindows.cardSearching.locationCard.OnTargetUser
import igor.second.spaceapp.appwindows.cardSearching.locationCard.SearchingScreen
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.LocationPermission
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.LocationReminderDialog
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.LocationViewModel
import kotlinx.coroutines.delay

@Composable
fun MainSearching(
    navController: NavController,
    locationViewModel: LocationViewModel = viewModel(),
    dataStoreManager: DataStoreManager,
    bronzeValue1: MutableState<Int>,
    bronzeValue2: MutableState<Int>,
    bronzeValue3: MutableState<Int>,
    bronzeValue4: MutableState<Int>,
    bronzeValue5: MutableState<Int>,
    bronzeValue6: MutableState<Int>,
    bronzeValue7: MutableState<Int>,
    bronzeValue8: MutableState<Int>,
    silverValue1: MutableState<Int>,
    silverValue2: MutableState<Int>,
    silverValue3: MutableState<Int>,
    silverValue4: MutableState<Int>,
    silverValue5: MutableState<Int>,
    silverValue6: MutableState<Int>,
    silverValue7: MutableState<Int>,
    silverValue8: MutableState<Int>,
    goldValue1: MutableState<Int>,
    goldValue2: MutableState<Int>,
    goldValue3: MutableState<Int>,
    goldValue4: MutableState<Int>,
    goldValue5: MutableState<Int>,
    goldValue6: MutableState<Int>,
    goldValue7: MutableState<Int>,
    goldValue8: MutableState<Int>,
    diamondValue1: MutableState<Int>,
    diamondValue2: MutableState<Int>,
    diamondValue3: MutableState<Int>,
    diamondValue4: MutableState<Int>,
    diamondValue5: MutableState<Int>,
    diamondValue6: MutableState<Int>,
    diamondValue7: MutableState<Int>,
    diamondValue8: MutableState<Int>,
    platinumValue1: MutableState<Int>,
    platinumValue2: MutableState<Int>,
    platinumValue3: MutableState<Int>,
    platinumValue4: MutableState<Int>,
    platinumValue5: MutableState<Int>,
    platinumValue6: MutableState<Int>,
    platinumValue7: MutableState<Int>,
    platinumValue8: MutableState<Int>,
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    epicValue5: MutableState<Int>,
    epicValue6: MutableState<Int>,
    epicValue7: MutableState<Int>,
    epicValue8: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
    userName: MutableState<String>,
) {

    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var hasLocationPermission by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var targetLocation by remember { mutableStateOf<Location?>(null) }
    var isTargetReached by remember { mutableStateOf(false) }

    var bearingToTarget by remember { mutableFloatStateOf(0f) }
    var compassDirection by remember { mutableFloatStateOf(0f) }
    var arrowBitmap by remember { mutableStateOf<Bitmap?>(null) }

    var distanceToTarget by remember { mutableDoubleStateOf(0.0) }
    var distanceMeters by remember { mutableDoubleStateOf(((10..20).random()).toDouble()) }

    val locationStatus by locationViewModel.locationStatus.collectAsState()
    var showLocationDialog by remember { mutableStateOf(false) }

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
            locationViewModel.startLocationUpdates(
                locationClient = locationClient,
                onLocationUpdate = { location ->
                    userLocation = location
                    targetLocation?.let { target ->
                        distanceToTarget = location.distanceTo(target).toDouble()
                        bearingToTarget = location.bearingTo(target)
                        isTargetReached = distanceToTarget < 3
                    }
                },
                context = context
            )
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            if (targetLocation == null) {
                targetLocation = locationViewModel.createTargetLocation(location, distanceMeters)
            }
        }
    }

    LaunchedEffect(userLocation, locationStatus) {
        if (userLocation == null) {
            showLocationDialog = false
            delay(5000L)
            if (!locationStatus) {
                showLocationDialog = true
                locationViewModel.locationStatusUpdate(context = context)
            }
        } else {
            showLocationDialog = false
        }
    }

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
                LocationPermission(
                    locationViewModel = locationViewModel,
                    context = context
                )
            }

            userLocation == null -> {
                OnSearchUser()
                if (showLocationDialog) {
                    LocationReminderDialog(
                        onDismiss = {
                            showLocationDialog = false
                            navController.navigate(Screens.MainIncome.route)
                        },
                        onEnableLocation = {
                            locationViewModel.openLocationSettings(context = context)
                            showLocationDialog = false
                        }
                    )
                }
            }

            isTargetReached -> {
                OnTargetUser(
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    platinumValue5 = platinumValue5,
                    platinumValue6 = platinumValue6,
                    platinumValue7 = platinumValue7,
                    platinumValue8 = platinumValue8,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    epicValue5 = epicValue5,
                    epicValue6 = epicValue6,
                    epicValue7 = epicValue7,
                    epicValue8 = epicValue8,
                    userMoneyValue = userMoneyValue,
                    dataStoreManager = dataStoreManager,
                    userGenerationLevel = userGenerationLevel,
                    userName = userName,
                    navController = navController
                )
            }

            else -> {
                SearchingScreen(
                    distanceToTarget = distanceToTarget,
                    arrowBitmap = arrowBitmap,
                    bearingToTarget = bearingToTarget,
                    compassDirection = compassDirection
                )
            }
        }
    }

    BackHandler {
        locationViewModel.stopLocationUpdates(locationClient)
        navController.navigate(Screens.MainIncome.route)
    }
}