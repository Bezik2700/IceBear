package igor.second.spaceapp.appsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    viewModel: MainViewModel = viewModel()
){

    val timer by viewModel.timer.collectAsState()
    val timerEnabled by viewModel.timerEnabled.collectAsState()
    var currentProgress by remember { mutableFloatStateOf(0f) }

    if (timerEnabled){
        LaunchedEffect (key1 = true) {
            launch {
                while (timer != 0){
                    loadProgress ( { progress ->
                        currentProgress = progress
                    },
                        timeMillis = timer.toLong() * 10
                    )
                }
            }
            launch {
                while (timer != 0){
                    delay(1000L)
                    viewModel.timerLoad()
                }
                viewModel.timerEnabledChange()
                viewModel.timerRestart()
                currentProgress = 0f
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
    ){
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { currentProgress }
        )
    }
}