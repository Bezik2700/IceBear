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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R

@Composable
fun SearchingScreen(
    modifier: Modifier = Modifier,
    distanceMeters: Double,
    distanceToTarget: Double,
    arrowBitmap: Bitmap?,
    bearingToTarget: Float,
    compassDirection: Float
){

    val relativeBearing = (bearingToTarget - compassDirection + 360) % 360
    val arrowRotation by animateFloatAsState(
        targetValue = relativeBearing,
        animationSpec = tween(durationMillis = 200)
    )

    Card (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 64.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "map",
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize().padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier.size(96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.Black.copy(alpha = 0.2f),
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
                Text("Дистанция до цели:")
                Text(
                    text = "${"%.1f".format(distanceToTarget)} метров",
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "Направление: ${"%.0f".format(relativeBearing)}°",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            LinearProgressIndicator(
                progress = { (1 - (distanceToTarget / distanceMeters)).toFloat() },
                modifier = Modifier
                    .width(320.dp)
                    .height(4.dp)
                    .rotate(90f)
                    .align(Alignment.CenterEnd)
                    .offset(y = (-120).dp)
            )
        }
    }
}