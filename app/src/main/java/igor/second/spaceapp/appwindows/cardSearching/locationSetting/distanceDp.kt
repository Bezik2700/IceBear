package igor.second.spaceapp.appwindows.cardSearching.locationSetting

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun calculateBottomPadding(
    distanceStart: Double,
    distanceToTarget: Double
): Dp {
    return when {
        distanceStart - distanceToTarget < distanceToTarget * 0.9 -> 560.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.8 -> 520.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.7 -> 480.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.6 -> 440.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.5 -> 360.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.4 -> 310.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.3 -> 260.dp
        distanceStart - distanceToTarget < distanceToTarget * 0.2 -> 230.dp
        else -> 200.dp
    }
}