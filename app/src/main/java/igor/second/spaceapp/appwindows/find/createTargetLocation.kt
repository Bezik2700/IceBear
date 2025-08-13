package igor.second.spaceapp.appwindows.find

import android.location.Location

// Создает цель на указанном расстоянии
internal fun createTargetLocation(baseLocation: Location, distanceMeters: Double): Location {
    return Location("target").apply {
        val offset = distanceMeters / 111000
        latitude = baseLocation.latitude + offset
        longitude = baseLocation.longitude + offset
    }
}