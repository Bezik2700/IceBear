package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(
    sliderPosition: MutableState<Float>,
    modifier: Modifier = Modifier
) {
    val currentLevel = sliderPosition.value.toInt()
    val animatedProgress by animateFloatAsState(
        targetValue = sliderPosition.value,
        animationSpec = tween(durationMillis = 300),
        label = "sliderAnimation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Анимированный заголовок
        var previousLevel by remember { mutableStateOf(currentLevel) }
        val animatedText by animateIntAsState(
            targetValue = currentLevel,
            animationSpec = tween(durationMillis = 300),
            label = "textAnimation"
        )

        LaunchedEffect(currentLevel) {
            previousLevel = currentLevel
        }

        Text(
            text = "Уровень $animatedText",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Слайдер с кастомным треком
        Slider(
            value = animatedProgress,
            onValueChange = { newValue -> sliderPosition.value = newValue },
            valueRange = 1f..6f,
            steps = 4,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Редкость карт под слайдером
        val rarityLabels = listOf("Бронза", "Серебро", "Золото", "Платина", "Алмаз", "Эпик")
        Text(
            text = rarityLabels.getOrNull(currentLevel - 1) ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Preview
@Composable
fun CardPreview(){
    CustomSlider(
        sliderPosition = remember { mutableStateOf(1f) }
    )
}