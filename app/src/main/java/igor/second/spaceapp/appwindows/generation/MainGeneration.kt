package igor.second.spaceapp.appwindows.generation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
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
import igor.second.spaceapp.appwindows.generation.cards.AutoGenerationCard
import igor.second.spaceapp.appwindows.generation.cards.MiniGeneration
import igor.second.spaceapp.settings.DataStoreManager

@Composable
fun MainGeneration(
    dataStoreManager: DataStoreManager,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
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
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    modifier: Modifier = Modifier,
    timerValue: MutableState<String>,
    timerRunning: MutableState<Boolean>
){

    var autoGenerationEnabler by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 32.dp)
    ) {
        FinishCard()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("money: ${userMoneyValue.value}")
            Switch(
                checked = autoGenerationEnabler,
                onCheckedChange = {
                    autoGenerationEnabler = it
                }
            )
        }
        if (autoGenerationEnabler){
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                AutoGenerationCard()
            }
        } else {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                TimerFromLevels(timerRunning = timerRunning)

                MiniGeneration(
                    timerValue = timerValue,
                    timerRunning = timerRunning,
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
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    userMoneyValue = userMoneyValue,
                    dataStoreManager = dataStoreManager,
                    userGenerationLevel = userGenerationLevel
                )
            }
        }
    }
    BackHandler {

    }
}