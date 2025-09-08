package igor.second.spaceapp.appwindows.gameProcess.gameCards.logic

import androidx.compose.runtime.MutableState

fun cardValueForGame(sliderPosition: MutableState<Float>, cardNumber: Int): Int {

    val cardValueForGame = when (cardNumber) {
        1 -> when (sliderPosition.value) {
            1f -> 10
            2f -> 90
            3f -> 170
            4f -> 250
            5f -> 330
            else -> 410
        }

        2 -> when (sliderPosition.value) {
            1f -> 20
            2f -> 100
            3f -> 180
            4f -> 260
            5f -> 340
            else -> 420
        }

        3 -> when (sliderPosition.value) {
            1f -> 30
            2f -> 110
            3f -> 190
            4f -> 270
            5f -> 350
            else -> 430
        }

        4 -> when (sliderPosition.value) {
            1f -> 40
            2f -> 120
            3f -> 200
            4f -> 280
            5f -> 360
            else -> 440
        }

        5 -> when (sliderPosition.value) {
            1f -> 50
            2f -> 130
            3f -> 210
            4f -> 290
            5f -> 370
            else -> 450
        }

        6 -> when (sliderPosition.value) {
            1f -> 60
            2f -> 140
            3f -> 220
            4f -> 300
            5f -> 380
            else -> 460
        }

        7 -> when (sliderPosition.value) {
            1f -> 70
            2f -> 150
            3f -> 230
            4f -> 310
            5f -> 390
            else -> 470
        }

        else -> when (sliderPosition.value) {
            1f -> 80
            2f -> 160
            3f -> 240
            4f -> 320
            5f -> 400
            else -> 480
        }
    }

    return cardValueForGame
}