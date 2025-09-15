package igor.second.spaceapp.appwindows.cardSearching.locationSetting

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

class LocationViewModel : ViewModel() {

    private var locationCallback: LocationCallback? = null

    // location on setting status
    private val _locationStatus = MutableStateFlow(false)
    val locationStatus: StateFlow<Boolean> = _locationStatus

    fun locationStatusUpdate(context: Context){
        _locationStatus.value = isLocationEnabled(context = context)
    }

    fun isLocationEnabled(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.isLocationEnabled
        } else {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }
    }

    fun openLocationSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    // location on setting status

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