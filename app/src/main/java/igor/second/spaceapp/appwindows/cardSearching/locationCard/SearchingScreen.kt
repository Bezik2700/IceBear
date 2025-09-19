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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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

    // Анимированное значение расстояния
    val animatedDistance by animateFloatAsState(
        targetValue = distanceToTarget.toFloat(),
        animationSpec = tween(durationMillis = 500),
        label = "distanceAnimation"
    )

    // Цвет текста в зависимости от расстояния
    val distanceColor by animateColorAsState(
        targetValue = when {
            distanceToTarget < 50 -> Color(0xFF4CAF50) // Зеленый для близкого расстояния
            distanceToTarget < 200 -> Color(0xFFFF9800) // Оранжевый для среднего
            else -> Color(0xFFF44336) // Красный для дальнего
        },
        animationSpec = tween(durationMillis = 300),
        label = "colorAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        // Фон с градиентом
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
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
                // Фоновое изображение с overlay
                Image(
                    painter = painterResource(image),
                    contentDescription = "Карта поиска",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Overlay градиент
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                                ),
                                startY = 0f,
                                endY = 500f
                            )
                        )
                )

                AnimationIcon(
                    distanceStart = distanceStart,
                    distanceToTarget = distanceToTarget,
                    locationViewModel = locationViewModel
                )

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    // Компас с указателем
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Внешний круг компаса
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(
                                color = Color.Red,
                                radius = size.minDimension / 2,
                                style = Stroke(width = 4f)
                            )
                        }

                        // Внутренний круг с градиентом
                        Canvas(modifier = Modifier.size(100.dp)) {
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color.Red,
                                        Color.Transparent
                                    ),
                                    center = Offset(size.width / 2, size.height / 2),
                                    radius = size.minDimension / 2
                                ),
                                radius = size.minDimension / 2
                            )
                        }

                        // Стрелка направления
                        arrowBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Направление к цели",
                                modifier = Modifier
                                    .size(80.dp)
                                    .rotate(arrowRotation)
                                    .graphicsLayer {
                                        rotationZ = arrowRotation
                                    },
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
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
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Расстояние до цели",
                                style = MaterialTheme.typography.labelMedium,
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

                    // Индикатор прогресса (опционально)
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
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
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

        // Кнопка смены фона (опционально)
        FloatingActionButton(
            onClick = { imageValue = ((imageValue % 5) + 1) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(48.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Сменить фон"
            )
        }
    }
}