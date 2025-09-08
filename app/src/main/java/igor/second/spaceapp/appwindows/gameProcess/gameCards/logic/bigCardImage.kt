package igor.second.spaceapp.appwindows.gameProcess.gameCards.logic

import igor.second.spaceapp.R

fun bigCardImage(lastCardValue: Any): Int {

    val image = when (lastCardValue){
        10 -> R.drawable.bronze_1
        20 -> R.drawable.bronze_2
        30 -> R.drawable.bronze_3
        40 -> R.drawable.bronze_4
        50 -> R.drawable.bronze_5
        60 -> R.drawable.bronze_6
        70 -> R.drawable.bronze_7
        80 -> R.drawable.bronze_8
        90 -> R.drawable.silver_1
        100 -> R.drawable.silver_2
        110 -> R.drawable.silver_3
        120 -> R.drawable.silver_4
        130 -> R.drawable.silver_5
        140 -> R.drawable.silver_6
        150 -> R.drawable.silver_7
        160 -> R.drawable.silver_8
        170 -> R.drawable.gold_1
        180 -> R.drawable.gold_2
        190 -> R.drawable.gold_3
        200 -> R.drawable.gold_4
        210 -> R.drawable.gold_5
        220 -> R.drawable.gold_6
        230 -> R.drawable.gold_7
        240 -> R.drawable.gold_8
        250 -> R.drawable.diamond_1
        260 -> R.drawable.diamond_2
        270 -> R.drawable.diamond_3
        280 -> R.drawable.diamond_4
        290 -> R.drawable.diamond_5
        300 -> R.drawable.diamond_6
        310 -> R.drawable.diamond_7
        320 -> R.drawable.diamond_8
        330 -> R.drawable.platinum_1
        340 -> R.drawable.platinum_2
        350 -> R.drawable.platinum_3
        360 -> R.drawable.platinum_4
        370 -> R.drawable.platinum_5
        380 -> R.drawable.platinum_6
        390 -> R.drawable.platinum_7
        400 -> R.drawable.platinum_8
        410 -> R.drawable.epic_1
        420 -> R.drawable.epic_2
        430 -> R.drawable.epic_3
        440 -> R.drawable.epic_4
        450 -> R.drawable.epic_5
        460 -> R.drawable.epic_6
        470 -> R.drawable.epic_7
        else -> R.drawable.epic_8
    }

    return image
}