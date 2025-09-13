package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.minicard.logic

import androidx.compose.runtime.MutableState
import igor.second.spaceapp.R

fun cardNumber(sliderPosition: MutableState<Float>, cardValue: Int): Int {

    val image1 = when(sliderPosition.value){
        1f -> R.drawable.bronze_1
        2f -> R.drawable.silver_1
        3f -> R.drawable.gold_1
        4f -> R.drawable.diamond_1
        5f -> R.drawable.platinum_1
        else -> R.drawable.epic_1
    }
    val image2 = when(sliderPosition.value){
        1f -> R.drawable.bronze_2
        2f -> R.drawable.silver_2
        3f -> R.drawable.gold_2
        4f -> R.drawable.diamond_2
        5f -> R.drawable.platinum_2
        else -> R.drawable.epic_2
    }
    val image3 = when(sliderPosition.value){
        1f -> R.drawable.bronze_3
        2f -> R.drawable.silver_3
        3f -> R.drawable.gold_3
        4f -> R.drawable.diamond_3
        5f -> R.drawable.platinum_3
        else -> R.drawable.epic_3
    }
    val image4 = when(sliderPosition.value){
        1f -> R.drawable.bronze_4
        2f -> R.drawable.silver_4
        3f -> R.drawable.gold_4
        4f -> R.drawable.diamond_4
        5f -> R.drawable.platinum_4
        else -> R.drawable.epic_4
    }
    val image5 = when(sliderPosition.value){
        1f -> R.drawable.bronze_5
        2f -> R.drawable.silver_5
        3f -> R.drawable.gold_5
        4f -> R.drawable.diamond_5
        5f -> R.drawable.platinum_5
        else -> R.drawable.epic_5
    }
    val image6 = when(sliderPosition.value){
        1f -> R.drawable.bronze_6
        2f -> R.drawable.silver_6
        3f -> R.drawable.gold_6
        4f -> R.drawable.diamond_6
        5f -> R.drawable.platinum_6
        else -> R.drawable.epic_6
    }
    val image7 = when(sliderPosition.value){
        1f -> R.drawable.bronze_7
        2f -> R.drawable.silver_7
        3f -> R.drawable.gold_7
        4f -> R.drawable.diamond_7
        5f -> R.drawable.platinum_7
        else -> R.drawable.epic_7
    }
    val image8 = when(sliderPosition.value){
        1f -> R.drawable.bronze_8
        2f -> R.drawable.silver_8
        3f -> R.drawable.gold_8
        4f -> R.drawable.diamond_8
        5f -> R.drawable.platinum_8
        else -> R.drawable.epic_8
    }

    var image = image1

    when (cardValue){
        1 -> image = image1
        2 -> image = image2
        3 -> image = image3
        4 -> image = image4
        5 -> image = image5
        6 -> image = image6
        7 -> image = image7
        8 -> image = image8
    }
    return image
}