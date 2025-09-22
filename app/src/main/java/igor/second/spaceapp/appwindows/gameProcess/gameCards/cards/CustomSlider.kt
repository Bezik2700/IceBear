package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        var previousLevel by remember { mutableIntStateOf(currentLevel) }

        LaunchedEffect(currentLevel) {
            previousLevel = currentLevel
        }

        Slider(
            value = animatedProgress,
            onValueChange = { newValue -> sliderPosition.value = newValue },
            valueRange = 1f..6f,
            steps = 4,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF9678B6),
                activeTrackColor = Color(0xFF9678B6),
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}