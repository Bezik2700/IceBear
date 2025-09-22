package igor.second.spaceapp.appwindows.cardSearching.locationCard

import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
) {
    val relativeBearing = (bearingToTarget - compassDirection + 360) % 360
    val arrowRotation by animateFloatAsState(
        targetValue = relativeBearing,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    val distanceStart = rememberSaveable { mutableDoubleStateOf(distanceToTarget) }
    var imageValue by remember { mutableIntStateOf((1..5).random()) }

    val image = when(imageValue) {
        1 -> R.drawable.searching_map_1
        2 -> R.drawable.searching_map_2
        3 -> R.drawable.searching_map_3
        4 -> R.drawable.searching_map_4
        else -> R.drawable.searching_map_5
    }

    LaunchedEffect(distanceToTarget > 0.0) {
        if (distanceStart.doubleValue == 0.0) {
            distanceStart.doubleValue = distanceToTarget
        }
    }

    val animatedDistance by animateFloatAsState(
        targetValue = distanceToTarget.toFloat(),
        animationSpec = tween(durationMillis = 500),
        label = "distanceAnimation"
    )

    val distanceColor by animateColorAsState(
        targetValue = when {
            distanceToTarget < 50 -> Color(0xFF4CAF50)
            distanceToTarget < 200 -> Color(0xFFFF9800)
            else -> Color(0xFFF44336)
        },
        animationSpec = tween(durationMillis = 300),
        label = "colorAnimation"
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 48.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(image),
                contentDescription = "Карта поиска",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(alpha = 1f)
                            ),
                            startY = 1000f,
                            endY = 0f
                        )
                    )
            )

            AnimationIcon(
                distanceStart = distanceStart,
                distanceToTarget = distanceToTarget
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color(0xFFA865A1),
                            radius = size.minDimension / 2,
                            style = Stroke(width = 4f)
                        )
                    }
                    Canvas(modifier = Modifier.size(80.dp)) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFA865A1),
                                    Color.Transparent
                                ),
                                center = Offset(size.width / 2, size.height / 2),
                                radius = size.minDimension / 2
                            ),
                            radius = size.minDimension / 2
                        )
                    }
                    arrowBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Направление к цели",
                            modifier = Modifier
                                .size(64.dp)
                                .rotate(arrowRotation)
                                .graphicsLayer {
                                    rotationZ = arrowRotation
                                },
                            colorFilter = ColorFilter.tint(Color(0xFFA865A1))
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(0xFFA865A1))
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.distance),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${"%.1f".format(animatedDistance)} м",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = distanceColor,
                            modifier = Modifier
                        )
                    }
                }

                if (distanceStart.doubleValue > 0) {
                    Spacer(modifier = Modifier.height(16.dp))

                    LinearProgressIndicator(
                        progress = {
                            val progress = 1f - (distanceToTarget / distanceStart.doubleValue).toFloat()
                            progress.coerceIn(0f, 1f)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xFFA865A1),
                        trackColor = Color(0xFFE1ADDB)
                    )

                    Text(
                        text = "Пройдено: ${"%.0f".format((1 - distanceToTarget / distanceStart.doubleValue) * 100)}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}