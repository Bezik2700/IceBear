package igor.second.spaceapp.camera

import android.Manifest
import android.app.Activity
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

@Composable
fun ARCompassApp() {
    val context = LocalContext.current
    val targetLocation = remember {
        Location("target").apply {
            latitude = 56.751244
            longitude = 38.618423
        }
    }

    // Состояния для разрешений
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var cameraPermissionGranted by remember { mutableStateOf(false) }
    var showRationale by remember { mutableStateOf(false) }

    // Лончер для запроса разрешений
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        locationPermissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        cameraPermissionGranted = permissions[Manifest.permission.CAMERA] == true

        // Проверяем, показывали ли объяснение
        showRationale = !locationPermissionGranted &&
                ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
    }

    // Первый запрос разрешений
    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            )
        )
    }

    // UI в зависимости от состояния разрешений
    when {
        !locationPermissionGranted && !cameraPermissionGranted -> {
            PermissionRequestScreen(
                showRationale = showRationale,
                onRequestPermission = {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA
                        )
                    )
                },
                onDenyPermission = {
                    // Действия при окончательном отказе
                }
            )
        }
        !locationPermissionGranted -> {
            LocationPermissionRequired()
        }
        !cameraPermissionGranted -> {
            CameraPermissionRequired()
        }
        else -> {
            ARCompassView(targetLocation)
        }
    }
}