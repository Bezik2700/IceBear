package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(sliderPosition: MutableState<Float>){
    Row (
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Slider(
            value = sliderPosition.value,
            onValueChange = { newValue -> sliderPosition.value = newValue },
            valueRange = 1f..6f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )
    }
}