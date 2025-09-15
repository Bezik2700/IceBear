package igor.second.spaceapp.appwindows.cardGeneration.generator.userGeneration

import androidx.compose.runtime.MutableState

fun generationButtonFun(
    generationValue: MutableState<Int>,
    value: Int
): Int {
    generationValue.value += value
    return generationValue.value
}