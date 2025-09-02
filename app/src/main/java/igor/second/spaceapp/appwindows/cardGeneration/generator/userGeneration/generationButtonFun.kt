package igor.second.spaceapp.appwindows.cardGeneration.generator.userGeneration

import androidx.compose.runtime.MutableState

fun generationButtonFun(
    userGenerationLevel: MutableState<Int>,
    value: Int
): Int {
    userGenerationLevel.value += value
    return userGenerationLevel.value
}