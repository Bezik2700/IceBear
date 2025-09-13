package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic

import androidx.compose.runtime.MutableState

fun cardScoreFun(
    sliderPosition: MutableState<Float>,
    cardNumber: Int,
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
    epicValue8: MutableState<Int>
): Int {

    val score1 = when(sliderPosition.value){
        1f -> bronzeValue1.value
        2f -> silverValue1.value
        3f -> goldValue1.value
        4f -> diamondValue1.value
        5f -> platinumValue1.value
        else -> epicValue1.value
    }
    val score2 = when(sliderPosition.value){
        1f -> bronzeValue2.value
        2f -> silverValue2.value
        3f -> goldValue2.value
        4f -> diamondValue2.value
        5f -> platinumValue2.value
        else -> epicValue2.value
    }
    val score3 = when(sliderPosition.value){
        1f -> bronzeValue3.value
        2f -> silverValue3.value
        3f -> goldValue3.value
        4f -> diamondValue3.value
        5f -> platinumValue3.value
        else -> epicValue3.value
    }
    val score4 = when(sliderPosition.value){
        1f -> bronzeValue4.value
        2f -> silverValue4.value
        3f -> goldValue4.value
        4f -> diamondValue4.value
        5f -> platinumValue4.value
        else -> epicValue4.value
    }
    val score5 = when(sliderPosition.value){
        1f -> bronzeValue5.value
        2f -> silverValue5.value
        3f -> goldValue5.value
        4f -> diamondValue5.value
        5f -> platinumValue5.value
        else -> epicValue5.value
    }
    val score6 = when(sliderPosition.value){
        1f -> bronzeValue6.value
        2f -> silverValue6.value
        3f -> goldValue6.value
        4f -> diamondValue6.value
        5f -> platinumValue6.value
        else -> epicValue6.value
    }
    val score7 = when(sliderPosition.value){
        1f -> bronzeValue7.value
        2f -> silverValue7.value
        3f -> goldValue7.value
        4f -> diamondValue7.value
        5f -> platinumValue7.value
        else -> epicValue7.value
    }
    val score8 = when(sliderPosition.value){
        1f -> bronzeValue8.value
        2f -> silverValue8.value
        3f -> goldValue8.value
        4f -> diamondValue8.value
        5f -> platinumValue8.value
        else -> epicValue8.value
    }

    val cardValue = when (cardNumber) {
        1 -> score1
        2 -> score2
        3 -> score3
        4 -> score4
        5 -> score5
        6 -> score6
        7 -> score7
        else -> score8
    }

    return cardValue
}