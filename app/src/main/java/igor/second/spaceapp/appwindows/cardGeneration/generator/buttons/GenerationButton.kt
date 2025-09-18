package igor.second.spaceapp.appwindows.cardGeneration.generator.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.cardGeneration.generator.userGeneration.generationButtonFun

@Composable
fun GenerationButton(
    modifier: Modifier = Modifier,
    generationValue: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
    color: Color
) {
    val value = calculateValueBasedOnLevel(userGenerationLevel.value)
    val progressColor = getProgressColor(generationValue.value)
    val progress = generationValue.value / 480f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(72.dp)
    ) {
        // Фоновая подсветка при высоком прогрессе
        if (progress > 0.8f) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            progressColor.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        center = center,
                        radius = size.minDimension / 2 * 0.8f
                    )
                )
            }
        }

        // Прогресс-бар
        CircularProgressIndicator(
            modifier = Modifier
                .size(72.dp)
                .rotate(-90f),
            color = progressColor,
            trackColor = Color.White.copy(alpha = 0.3f),
            progress = progress,
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round
        )

        // Основная кнопка
        Card(
            modifier = Modifier
                .size(56.dp)
                .clickable {
                    generationButtonFun(
                        generationValue = generationValue,
                        value = value
                    )
                },
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = color)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                color,
                                color.copy(alpha = 0.8f)
                            )
                        )
                    )
            ) {
                Text(
                    text = "$value",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier
                        .shadow(2.dp, shape = CircleShape)
                        .padding(2.dp)
                )
            }
        }

        // Эффект блика
        Canvas(
            modifier = Modifier.size(56.dp)
        ) {
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.3f),
                        Color.Transparent
                    ),
                    start = Offset(size.width * 0.2f, size.height * 0.2f),
                    end = Offset(size.width * 0.6f, size.height * 0.6f)
                ),
                radius = size.minDimension / 2
            )
        }
    }
}

private fun calculateValueBasedOnLevel(level: Int): Int {
    return when (level) {
        0 -> (1..5).random()
        in 1..2 -> (2..6).random()
        in 3..6 -> (3..7).random()
        in 7..18 -> (4..8).random()
        else -> (5..9).random()
    }
}

private fun getProgressColor(generationValue: Int): Color {
    return when (generationValue) {
        in 0..80 -> Color(0xFFFF9800)
        in 81..160 -> Color(0xFFE0E0E0)
        in 161..240 -> Color(0xFFFFEB3B)
        in 241..320 -> Color(0xFF2196F3)
        in 321..400 -> Color(0xFFF44336)
        else -> Color(0xFF4CAF50)
    }
}