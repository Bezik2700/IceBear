package igor.second.spaceapp.appwindows.cardSearching.canvas

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.calculateBottomPadding
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.LocationViewModel

@Composable
fun AnimationIcon(
    modifier: Modifier = Modifier,
    distanceToTarget: Double,
    distanceStart: MutableState<Double>,
    locationViewModel: LocationViewModel
){

    var animationState by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000),
                    repeatMode = RepeatMode.Restart
                )
            ) { value, _ ->
                animationState = value
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()){
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = "target",
            tint = Color.Red,
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = calculateBottomPadding(
                    distanceStart = distanceStart.value,
                    distanceToTarget = distanceToTarget
                )
                )
        )
        for (i in 0..2) {

            val scale = 1f + animationState + i * 0.5f
            val alpha = 1f - (animationState + i * 0.2f).coerceIn(0f, 1f)

            Canvas(modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = calculateBottomPadding(
                    distanceStart = distanceStart.value,
                    distanceToTarget = distanceToTarget
                )
                )
            ) {
                drawCircle(
                    color = Color.Red.copy(alpha = alpha * 1f),
                    radius = 30.dp.toPx() * scale,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}