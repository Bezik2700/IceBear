package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameMiniCard(
    @DrawableRes image: Int,
    onClick: () -> Unit,
    cardScore: Int,
    cardValue: Int
) {
    val isEnabled = cardScore != 0
    val colorMatrix = remember { ColorMatrix().apply { setToSaturation(0f) } }

    // Анимация для клика
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "cardScale"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        // Бейдж с количеством очков
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isEnabled) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                }
            ),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = cardScore.toString(),
                color = if (isEnabled) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .size(24.dp)
                    .wrapContentSize(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Основная карточка
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isEnabled) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                }
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (isEnabled) 6.dp else 2.dp,
                pressedElevation = if (isEnabled) 2.dp else 1.dp
            ),
            modifier = Modifier
                .size(width = 70.dp, height = 100.dp)
                .graphicsLayer { scaleX = scale; scaleY = scale }
                .clickable(
                    enabled = isEnabled,
                    onClick = onClick,
                    interactionSource = interactionSource
                )
                .border(
                    width = 2.dp,
                    color = if (isEnabled) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    },
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Фон карточки
                Image(
                    painter = painterResource(image),
                    contentDescription = "Card image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(if (isEnabled) 1f else 0.4f),
                    colorFilter = if (isEnabled) null else ColorFilter.colorMatrix(colorMatrix)
                )

                // Градиентная overlay для лучшей читаемости текста
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                ),
                                startY = 0f,
                                endY = 100f
                            )
                        )
                )

                // Значение карты в углу
                Text(
                    text = cardValue.toString(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(6.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = CircleShape
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )

                // Эффект блеска для доступных карт
                if (isEnabled) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.1f),
                                        Color.Transparent
                                    ),
                                    center = Offset(0.7f, 0.3f),
                                    radius = 200f
                                )
                            )
                    )
                } else {
                    // Перечеркивание для недоступных карт
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.2f))
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawLine(
                                color = Color.Red.copy(alpha = 0.6f),
                                start = Offset(0f, 0f),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                            drawLine(
                                color = Color.Red.copy(alpha = 0.6f),
                                start = Offset(size.width, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                    }
                }
            }
        }
    }
}
