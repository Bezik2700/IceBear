package igor.second.spaceapp.appwindows.cardGeneration.cards

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.TimerViewModel

@Composable
fun MiniGeneration(
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
    dataStoreManager: DataStoreManager,
    bronzeValue1: MutableState<Int>,
    bronzeValue2: MutableState<Int>,
    bronzeValue3: MutableState<Int>,
    bronzeValue4: MutableState<Int>,
    bronzeValue5: MutableState<Int>,
    bronzeValue6: MutableState<Int>,
    bronzeValue7: MutableState<Int>,
    bronzeValue8: MutableState<Int>,
    silverValue1: MutableState<Int>,
    silverValue2: MutableState<Int>,
    silverValue3: MutableState<Int>,
    silverValue4: MutableState<Int>,
    silverValue5: MutableState<Int>,
    silverValue6: MutableState<Int>,
    silverValue7: MutableState<Int>,
    silverValue8: MutableState<Int>,
    goldValue1: MutableState<Int>,
    goldValue2: MutableState<Int>,
    goldValue3: MutableState<Int>,
    goldValue4: MutableState<Int>,
    goldValue5: MutableState<Int>,
    goldValue6: MutableState<Int>,
    goldValue7: MutableState<Int>,
    goldValue8: MutableState<Int>,
    diamondValue1: MutableState<Int>,
    diamondValue2: MutableState<Int>,
    diamondValue3: MutableState<Int>,
    diamondValue4: MutableState<Int>,
    diamondValue5: MutableState<Int>,
    diamondValue6: MutableState<Int>,
    diamondValue7: MutableState<Int>,
    diamondValue8: MutableState<Int>,
    platinumValue1: MutableState<Int>,
    platinumValue2: MutableState<Int>,
    platinumValue3: MutableState<Int>,
    platinumValue4: MutableState<Int>,
    platinumValue5: MutableState<Int>,
    platinumValue6: MutableState<Int>,
    platinumValue7: MutableState<Int>,
    platinumValue8: MutableState<Int>,
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    epicValue5: MutableState<Int>,
    epicValue6: MutableState<Int>,
    epicValue7: MutableState<Int>,
    epicValue8: MutableState<Int>,
    viewModel: TimerViewModel = viewModel()
){

    val timerEnabled by viewModel.timerEnabled.collectAsState()

    LaunchedEffect(key1 = null) {
        if (timerEnabled){
            viewModel.timerEnabledChange()
        }
    }

    if (timerEnabled){
        Box(modifier = Modifier){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator(
                    modifier = Modifier.size(160.dp),
                    color = when (userGenerationLevel.value){
                        in 0..80 -> Color(0xFFE7931D)
                        in 81..160 -> Color(0xFFD3CDCA)
                        in 161..240 -> Color(0xFFFFE300)
                        in 241..320 -> Color(0xFF0091FF)
                        in 321..400 -> Color(0xFFFF0000)
                        else -> Color(0xFF00FF0D)},
                    trackColor = Color.Blue,
                    progress = { userGenerationLevel.value / 500.toFloat() }
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
                        onClick = { userGenerationLevel.value += 2 }) {
                        Text("+ 1")
                    }
                    Button(
                        onClick = { userGenerationLevel.value *= 2 }) {
                        Text("- 1")
                    }
                }
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { userGenerationLevel.value /= 2 }) {
                        Text("+ 1")
                    }
                    Button(
                        onClick = { userGenerationLevel.value -= 2 }) {
                        Text("- 1")
                    }
                }
            }
        }
    } else {
        if (userGenerationLevel.value != 0){
            InCollectionButton(
                bronzeValue1 = bronzeValue1,
                bronzeValue2 = bronzeValue2,
                bronzeValue3 = bronzeValue3,
                bronzeValue4 = bronzeValue4,
                bronzeValue5 = bronzeValue5,
                bronzeValue6 = bronzeValue6,
                bronzeValue7 = bronzeValue7,
                bronzeValue8 = bronzeValue8,
                silverValue1 = silverValue1,
                silverValue2 = silverValue2,
                silverValue3 = silverValue3,
                silverValue4 = silverValue4,
                silverValue5 = silverValue5,
                silverValue6 = silverValue6,
                silverValue7 = silverValue7,
                silverValue8 = silverValue8,
                goldValue1 = goldValue1,
                goldValue2 = goldValue2,
                goldValue3 = goldValue3,
                goldValue4 = goldValue4,
                goldValue5 = goldValue5,
                goldValue6 = goldValue6,
                goldValue7 = goldValue7,
                goldValue8 = goldValue8,
                diamondValue1 = diamondValue1,
                diamondValue2 = diamondValue2,
                diamondValue3 = diamondValue3,
                diamondValue4 = diamondValue4,
                diamondValue5 = diamondValue5,
                diamondValue6 = diamondValue6,
                diamondValue7 = diamondValue7,
                diamondValue8 = diamondValue8,
                platinumValue1 = platinumValue1,
                platinumValue2 = platinumValue2,
                platinumValue3 = platinumValue3,
                platinumValue4 = platinumValue4,
                platinumValue5 = platinumValue5,
                platinumValue6 = platinumValue6,
                platinumValue7 = platinumValue7,
                platinumValue8 = platinumValue8,
                epicValue1 = epicValue1,
                epicValue2 = epicValue2,
                epicValue3 = epicValue3,
                epicValue4 = epicValue4,
                epicValue5 = epicValue5,
                epicValue6 = epicValue6,
                epicValue7 = epicValue7,
                epicValue8 = epicValue8,
                userMoneyValue = userMoneyValue,
                dataStoreManager = dataStoreManager,
                userGenerationLevel = userGenerationLevel
            )
        } else {
            Button(onClick = {
                userMoneyValue.value -= 1
                viewModel.timerRestart()
                viewModel.timerEnabledChange()
            }) {
                Text("Start")
            }
        }
    }
}