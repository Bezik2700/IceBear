package igor.second.spaceapp.generation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun loadProgress(
    updateProgress: (Float) -> Unit,
    timeMillis: Long
) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(timeMillis)
    }
}

@Composable
fun TimerFromLevels(
    timerRunning: MutableState<Boolean>,
    timerValue: MutableState<String>,
){

    var timer by remember { mutableIntStateOf(timerValue.value.toInt()) }

    var currentProgress by remember { mutableFloatStateOf(0f) }

    if (timerRunning.value){
        LaunchedEffect (key1 = true) {
            launch {
                while (timer != 0){
                    loadProgress ( { progress ->
                        currentProgress = progress
                    },
                        timeMillis = timerValue.value.toLong() * 10
                    )
                }
            }
            launch {
                while (timer != 0){
                    delay(1000L)
                    timer -= 1
                }
                timerRunning.value = false
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        LinearProgressIndicator(
            progress = { currentProgress }
        )
    }
}