package igor.second.spaceapp.generation.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BigGeneration(
    timerRunning: MutableState<Boolean>,
    timerValue: MutableState<String>,
    cardValue: MutableState<Float>
){

    if (timerRunning.value){
        Box(modifier = Modifier){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator(
                    modifier = Modifier.size(160.dp),
                    progress = { cardValue.value / 100 }
                )
            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ){
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { cardValue.value += 1 }) {
                        Text("+ 1")
                    }
                    Button(
                        onClick = { cardValue.value -= 1 }) {
                        Text("- 1")
                    }
                }
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { cardValue.value += 1 }) {
                        Text("+ 1")
                    }
                    Button(
                        onClick = { cardValue.value -= 1 }) {
                        Text("- 1")
                    }
                }
            }
        }
    } else {
        Button(onClick = {
            timerValue.value = "10"
            cardValue.value = 0f
            timerRunning.value = true
        }) {
            Text("Start")
        }
    }
}