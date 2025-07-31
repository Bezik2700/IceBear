package igor.second.spaceapp.generation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainGeneration(
    navController: NavController,
    modifier: Modifier = Modifier,
    cardValue: MutableState<Int>,
    timerValue: MutableState<String>,
    timerRunning: MutableState<Boolean>
){

    var autoGenerationEnabler by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        FinishCard()
        Button(onClick = {autoGenerationEnabler = !autoGenerationEnabler}) { Text(autoGenerationEnabler.toString()) }
        if (autoGenerationEnabler){
            AutoGenerationCard()
        } else {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                TimerFromLevels(
                    timerRunning = timerRunning,
                    timerValue = timerValue
                )
            }
            Text(cardValue.value.toString())
            UserGenerationCard(
                timerValue = timerValue,
                timerRunning = timerRunning,
                cardValue = cardValue
            )
        }
    }
}