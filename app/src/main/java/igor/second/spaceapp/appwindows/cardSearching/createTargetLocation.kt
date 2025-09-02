package igor.second.spaceapp.appwindows.cardSearching

import android.location.Location

fun createTargetLocation(baseLocation: Location, distanceMeters: Double): Location {
    return Location("target").apply {
        val offset = distanceMeters / 11100
        latitude = baseLocation.latitude + offset
        longitude = baseLocation.longitude + offset
    }
}