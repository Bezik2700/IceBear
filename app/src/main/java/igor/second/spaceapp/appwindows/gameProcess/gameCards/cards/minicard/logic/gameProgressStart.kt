package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic

import androidx.compose.runtime.MutableState
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.SettingData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun gameProcessStart(
    sliderPosition: MutableState<Float>,
    cardNumber: Int,
    dataStoreManager: DataStoreManager,
    userName: MutableState<String>,
    userMoneyValue: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
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
) = runBlocking {
    launch {
        when (cardNumber) {
            1 -> when (sliderPosition.value) {
                1f -> bronzeValue1.value -= 1
                2f -> silverValue1.value -= 1
                3f -> goldValue1.value -= 1
                4f -> diamondValue1.value -= 1
                5f -> platinumValue1.value -= 1
                else -> epicValue1.value -= 1
            }

            2 -> when (sliderPosition.value) {
                1f -> bronzeValue2.value -= 1
                2f -> silverValue2.value -= 1
                3f -> goldValue2.value -= 1
                4f -> diamondValue2.value -= 1
                5f -> platinumValue2.value -= 1
                else -> epicValue2.value -= 1
            }

            3 -> when (sliderPosition.value) {
                1f -> bronzeValue3.value -= 1
                2f -> silverValue3.value -= 1
                3f -> goldValue3.value -= 1
                4f -> diamondValue3.value -= 1
                5f -> platinumValue3.value -= 1
                else -> epicValue3.value -= 1
            }

            4 -> when (sliderPosition.value) {
                1f -> bronzeValue4.value -= 1
                2f -> silverValue4.value -= 1
                3f -> goldValue4.value -= 1
                4f -> diamondValue4.value -= 1
                5f -> platinumValue4.value -= 1
                else -> epicValue4.value -= 1
            }

            5 -> when (sliderPosition.value) {
                1f -> bronzeValue5.value -= 1
                2f -> silverValue5.value -= 1
                3f -> goldValue5.value -= 1
                4f -> diamondValue5.value -= 1
                5f -> platinumValue5.value -= 1
                else -> epicValue5.value -= 1
            }

            6 -> when (sliderPosition.value) {
                1f -> bronzeValue6.value -= 1
                2f -> silverValue6.value -= 1
                3f -> goldValue6.value -= 1
                4f -> diamondValue6.value -= 1
                5f -> platinumValue6.value -= 1
                else -> epicValue6.value -= 1
            }

            7 -> when (sliderPosition.value) {
                1f -> bronzeValue7.value -= 1
                2f -> silverValue7.value -= 1
                3f -> goldValue7.value -= 1
                4f -> diamondValue7.value -= 1
                5f -> platinumValue7.value -= 1
                else -> epicValue7.value -= 1
            }

            8 -> when (sliderPosition.value) {
                1f -> bronzeValue8.value -= 1
                2f -> silverValue8.value -= 1
                3f -> goldValue8.value -= 1
                4f -> diamondValue8.value -= 1
                5f -> platinumValue8.value -= 1
                else -> epicValue8.value -= 1
            }
        }
    }
    launch {
        dataStoreManager.saveSettings(
            SettingData(
                bronzeValue1 = bronzeValue1.value,
                bronzeValue2 = bronzeValue2.value,
                bronzeValue3 = bronzeValue3.value,
                bronzeValue4 = bronzeValue4.value,
                bronzeValue5 = bronzeValue5.value,
                bronzeValue6 = bronzeValue6.value,
                bronzeValue7 = bronzeValue7.value,
                bronzeValue8 = bronzeValue8.value,
                silverValue1 = silverValue1.value,
                silverValue2 = silverValue2.value,
                silverValue3 = silverValue3.value,
                silverValue4 = silverValue4.value,
                silverValue5 = silverValue5.value,
                silverValue6 = silverValue6.value,
                silverValue7 = silverValue7.value,
                silverValue8 = silverValue8.value,
                goldValue1 = goldValue1.value,
                goldValue2 = goldValue2.value,
                goldValue3 = goldValue3.value,
                goldValue4 = goldValue4.value,
                goldValue5 = goldValue5.value,
                goldValue6 = goldValue6.value,
                goldValue7 = goldValue7.value,
                goldValue8 = goldValue8.value,
                diamondValue1 = diamondValue1.value,
                diamondValue2 = diamondValue2.value,
                diamondValue3 = diamondValue3.value,
                diamondValue4 = diamondValue4.value,
                diamondValue5 = diamondValue5.value,
                diamondValue6 = diamondValue6.value,
                diamondValue7 = diamondValue7.value,
                diamondValue8 = diamondValue8.value,
                platinumValue1 = platinumValue1.value,
                platinumValue2 = platinumValue2.value,
                platinumValue3 = platinumValue3.value,
                platinumValue4 = platinumValue4.value,
                platinumValue5 = platinumValue5.value,
                platinumValue6 = platinumValue6.value,
                platinumValue7 = platinumValue7.value,
                platinumValue8 = platinumValue8.value,
                epicValue1 = epicValue1.value,
                epicValue2 = epicValue2.value,
                epicValue3 = epicValue3.value,
                epicValue4 = epicValue4.value,
                epicValue5 = epicValue5.value,
                epicValue6 = epicValue6.value,
                epicValue7 = epicValue7.value,
                epicValue8 = epicValue8.value,
                userGenerationLevel = userGenerationLevel.value,
                userMoneyValue = userMoneyValue.value,
                userName = userName.value
            )
        )
    }
}