package igor.second.spaceapp.appwindows.cardSearching.locationCard

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R
import igor.second.spaceapp.appwindows.cardSearching.canvas.AnimationIcon
import igor.second.spaceapp.appwindows.cardSearching.locationSetting.LocationViewModel

@Composable
fun SearchingScreen(
    modifier: Modifier = Modifier,
    distanceToTarget: Double,
    arrowBitmap: Bitmap?,
    bearingToTarget: Float,
    compassDirection: Float,
    locationViewModel: LocationViewModel
){

    val relativeBearing = (bearingToTarget - compassDirection + 360) % 360
    val arrowRotation by animateFloatAsState(
        targetValue = relativeBearing,
        animationSpec = tween(durationMillis = 200)
    )
    val distanceStart = rememberSaveable { mutableDoubleStateOf(distanceToTarget) }
    var imageValue = remember { mutableIntStateOf((1..5).random()) }
    val image = when(imageValue.intValue){
        1 -> R.drawable.searching_map_1
        2 -> R.drawable.searching_map_2
        3 -> R.drawable.searching_map_3
        4 -> R.drawable.searching_map_4
        else -> R.drawable.searching_map_5
    }

    LaunchedEffect(distanceToTarget > 0.0) {
        if (distanceStart.doubleValue == 0.0){
            distanceStart.doubleValue = distanceToTarget
        }
    }

    Card (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 64.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painterResource(image),
                contentDescription = "map",
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            AnimationIcon(
                distanceStart = distanceStart,
                distanceToTarget = distanceToTarget,
                locationViewModel = locationViewModel
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize().padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier.size(96.dp).padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.Red.copy(alpha = 0.4f),
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
                Card (
                    modifier = modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${"%.1f".format(distanceToTarget)} m",
                        modifier = modifier.align(alignment = Alignment.CenterHorizontally),
                        color = Color.Red,
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
    }
}