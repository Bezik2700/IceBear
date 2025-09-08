package igor.second.spaceapp.appwindows.gameProcess.gameCards.logic

import androidx.compose.runtime.MutableState
import igor.second.spaceapp.R

fun messageTextGenerate(sliderPosition: MutableState<Float>, cardNumber: Int): Int {

    val text1 = when(sliderPosition.value){
        1f -> R.string.bronze1
        2f -> R.string.silver1
        3f -> R.string.gold1
        4f -> R.string.diamond1
        5f -> R.string.platinum1
        else -> R.string.epic1
    }
    val text2 = when(sliderPosition.value){
        1f -> R.string.bronze2
        2f -> R.string.silver2
        3f -> R.string.gold2
        4f -> R.string.diamond2
        5f -> R.string.platinum2
        else -> R.string.epic2
    }
    val text3 = when(sliderPosition.value){
        1f -> R.string.bronze3
        2f -> R.string.silver3
        3f -> R.string.gold3
        4f -> R.string.diamond3
        5f -> R.string.platinum3
        else -> R.string.epic3
    }
    val text4 = when(sliderPosition.value){
        1f -> R.string.bronze4
        2f -> R.string.silver4
        3f -> R.string.gold4
        4f -> R.string.diamond4
        5f -> R.string.platinum4
        else -> R.string.epic4
    }
    val text5 = when(sliderPosition.value){
        1f -> R.string.bronze5
        2f -> R.string.silver5
        3f -> R.string.gold5
        4f -> R.string.diamond5
        5f -> R.string.platinum5
        else -> R.string.epic5
    }
    val text6 = when(sliderPosition.value){
        1f -> R.string.bronze6
        2f -> R.string.silver6
        3f -> R.string.gold6
        4f -> R.string.diamond6
        5f -> R.string.platinum6
        else -> R.string.epic6
    }
    val text7 = when(sliderPosition.value){
        1f -> R.string.bronze7
        2f -> R.string.silver7
        3f -> R.string.gold7
        4f -> R.string.diamond7
        5f -> R.string.platinum7
        else -> R.string.epic7
    }
    val text8 = when(sliderPosition.value){
        1f -> R.string.bronze8
        2f -> R.string.silver8
        3f -> R.string.gold8
        4f -> R.string.diamond8
        5f -> R.string.platinum8
        else -> R.string.epic8
    }

    val cardValue = when (cardNumber) {
        1 -> text1
        2 -> text2
        3 -> text3
        4 -> text4
        5 -> text5
        6 -> text6
        7 -> text7
        else -> text8
    }

    return cardValue

}