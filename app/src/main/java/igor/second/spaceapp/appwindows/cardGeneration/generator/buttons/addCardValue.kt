package igor.second.spaceapp.appwindows.cardGeneration.generator.buttons

import androidx.compose.runtime.MutableState
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.SettingData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun addCardValue(
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
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
    userName: MutableState<String>,
    generationValue: MutableState<Int>
) = runBlocking {
    launch {
        when (generationValue.value){
            in 1..10 -> bronzeValue1.value += 1
            in 11..20 -> bronzeValue2.value += 1
            in 21..30 -> bronzeValue3.value += 1
            in 31..40 -> bronzeValue4.value += 1
            in 41..50 -> bronzeValue5.value += 1
            in 51..60 -> bronzeValue6.value += 1
            in 61..70 -> bronzeValue7.value += 1
            in 71..80 -> bronzeValue8.value += 1
            in 81..90 -> silverValue1.value += 1
            in 91..100 -> silverValue2.value += 1
            in 101..110 -> silverValue3.value += 1
            in 111..120 -> silverValue4.value += 1
            in 121..130 -> silverValue5.value += 1
            in 131..140 -> silverValue6.value += 1
            in 141..150 -> silverValue7.value += 1
            in 151..160 -> silverValue8.value += 1
            in 161..170 -> goldValue1.value += 1
            in 171..180 -> goldValue2.value += 1
            in 181..190 -> goldValue3.value += 1
            in 191..200 -> goldValue4.value += 1
            in 201..210 -> goldValue5.value += 1
            in 211..220 -> goldValue6.value += 1
            in 221..230 -> goldValue7.value += 1
            in 231..240 -> goldValue8.value += 1
            in 241..250 -> diamondValue1.value += 1
            in 251..260 -> diamondValue2.value += 1
            in 261..270 -> diamondValue3.value += 1
            in 271..280 -> diamondValue4.value += 1
            in 281..290 -> diamondValue5.value += 1
            in 291..300 -> diamondValue6.value += 1
            in 301..310 -> diamondValue7.value += 1
            in 311..320 -> diamondValue8.value += 1
            in 321..330 -> platinumValue1.value += 1
            in 331..340 -> platinumValue2.value += 1
            in 341..350 -> platinumValue3.value += 1
            in 351..360 -> platinumValue4.value += 1
            in 361..370 -> platinumValue5.value += 1
            in 371..380 -> platinumValue6.value += 1
            in 381..390 -> platinumValue7.value += 1
            in 391..400 -> platinumValue8.value += 1
            in 401..410 -> epicValue1.value += 1
            in 411..420 -> epicValue2.value += 1
            in 421..430 -> epicValue3.value += 1
            in 431..440 -> epicValue4.value += 1
            in 441..450 -> epicValue5.value += 1
            in 451..460 -> epicValue6.value += 1
            in 461..470 -> epicValue7.value += 1
            in 471..480 -> epicValue8.value += 1
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
                userMoneyValue = userMoneyValue.value,
                userGenerationLevel = userGenerationLevel.value,
                userName = userName.value
            )
        )
    }
}