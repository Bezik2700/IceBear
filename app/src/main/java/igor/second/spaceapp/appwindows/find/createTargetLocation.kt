package igor.second.spaceapp.appwindows.find

import android.location.Location

internal fun createTargetLocation(baseLocation: Location, distanceMeters: Double): Location {

    return Location("target").apply {
        val offset = distanceMeters / 11100
        latitude = baseLocation.latitude + offset
        longitude = baseLocation.longitude + offset
    }
}