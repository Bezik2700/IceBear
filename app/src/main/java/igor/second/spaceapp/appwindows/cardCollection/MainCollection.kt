package igor.second.spaceapp.appwindows.cardCollection

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appwindows.Screens

@Composable
fun MainCollection(
    modifier: Modifier = Modifier,
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
    navController: NavController
){

    LazyColumn (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 64.dp)
    ) {
        item {
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.bronze_1,
                cardImage2 = R.drawable.bronze_2,
                cardImage3 = R.drawable.bronze_3,
                cardImage4 = R.drawable.bronze_4,
                cardImage5 = R.drawable.bronze_5,
                cardImage6 = R.drawable.bronze_6,
                cardImage7 = R.drawable.bronze_7,
                cardImage8 = R.drawable.bronze_8,
                cardName1 = R.string.bronze1,
                cardName2 = R.string.bronze2,
                cardName3 = R.string.bronze3,
                cardName4 = R.string.bronze4,
                cardName5 = R.string.bronze5,
                cardName6 = R.string.bronze6,
                cardName7 = R.string.bronze7,
                cardName8 = R.string.bronze8,
                cardScore1 = bronzeValue1.value,
                cardScore2 = bronzeValue2.value,
                cardScore3 = bronzeValue3.value,
                cardScore4 = bronzeValue4.value,
                cardScore5 = bronzeValue5.value,
                cardScore6 = bronzeValue6.value,
                cardScore7 = bronzeValue7.value,
                cardScore8 = bronzeValue8.value,
                collectionName = R.string.bronze
            )
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.silver_1,
                cardImage2 = R.drawable.silver_2,
                cardImage3 = R.drawable.silver_3,
                cardImage4 = R.drawable.silver_4,
                cardImage5 = R.drawable.silver_5,
                cardImage6 = R.drawable.silver_6,
                cardImage7 = R.drawable.silver_7,
                cardImage8 = R.drawable.silver_8,
                cardName1 = R.string.silver1,
                cardName2 = R.string.silver2,
                cardName3 = R.string.silver3,
                cardName4 = R.string.silver4,
                cardName5 = R.string.silver5,
                cardName6 = R.string.silver6,
                cardName7 = R.string.silver7,
                cardName8 = R.string.silver8,
                cardScore1 = silverValue1.value,
                cardScore2 = silverValue2.value,
                cardScore3 = silverValue3.value,
                cardScore4 = silverValue4.value,
                cardScore5 = silverValue5.value,
                cardScore6 = silverValue6.value,
                cardScore7 = silverValue7.value,
                cardScore8 = silverValue8.value,
                collectionName = R.string.silver
            )
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.gold_1,
                cardImage2 = R.drawable.gold_2,
                cardImage3 = R.drawable.gold_3,
                cardImage4 = R.drawable.gold_4,
                cardImage5 = R.drawable.gold_5,
                cardImage6 = R.drawable.gold_6,
                cardImage7 = R.drawable.gold_7,
                cardImage8 = R.drawable.gold_8,
                cardName1 = R.string.gold1,
                cardName2 = R.string.gold2,
                cardName3 = R.string.gold3,
                cardName4 = R.string.gold4,
                cardName5 = R.string.gold5,
                cardName6 = R.string.gold6,
                cardName7 = R.string.gold7,
                cardName8 = R.string.gold8,
                cardScore1 = goldValue1.value,
                cardScore2 = goldValue2.value,
                cardScore3 = goldValue3.value,
                cardScore4 = goldValue4.value,
                cardScore5 = goldValue5.value,
                cardScore6 = goldValue6.value,
                cardScore7 = goldValue7.value,
                cardScore8 = goldValue8.value,
                collectionName = R.string.gold
            )
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.diamond_1,
                cardImage2 = R.drawable.diamond_2,
                cardImage3 = R.drawable.diamond_3,
                cardImage4 = R.drawable.diamond_4,
                cardImage5 = R.drawable.diamond_5,
                cardImage6 = R.drawable.diamond_6,
                cardImage7 = R.drawable.diamond_7,
                cardImage8 = R.drawable.diamond_8,
                cardName1 = R.string.diamond1,
                cardName2 = R.string.diamond2,
                cardName3 = R.string.diamond3,
                cardName4 = R.string.diamond4,
                cardName5 = R.string.diamond5,
                cardName6 = R.string.diamond6,
                cardName7 = R.string.diamond7,
                cardName8 = R.string.diamond8,
                cardScore1 = diamondValue1.value,
                cardScore2 = diamondValue2.value,
                cardScore3 = diamondValue3.value,
                cardScore4 = diamondValue4.value,
                cardScore5 = diamondValue5.value,
                cardScore6 = diamondValue6.value,
                cardScore7 = diamondValue7.value,
                cardScore8 = diamondValue8.value,
                collectionName = R.string.diamond
            )
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.platinum_1,
                cardImage2 = R.drawable.platinum_2,
                cardImage3 = R.drawable.platinum_3,
                cardImage4 = R.drawable.platinum_4,
                cardImage5 = R.drawable.platinum_5,
                cardImage6 = R.drawable.platinum_6,
                cardImage7 = R.drawable.platinum_7,
                cardImage8 = R.drawable.platinum_8,
                cardName1 = R.string.platinum1,
                cardName2 = R.string.platinum2,
                cardName3 = R.string.platinum3,
                cardName4 = R.string.platinum4,
                cardName5 = R.string.platinum5,
                cardName6 = R.string.platinum6,
                cardName7 = R.string.platinum7,
                cardName8 = R.string.platinum8,
                cardScore1 = platinumValue1.value,
                cardScore2 = platinumValue2.value,
                cardScore3 = platinumValue3.value,
                cardScore4 = platinumValue4.value,
                cardScore5 = platinumValue5.value,
                cardScore6 = platinumValue6.value,
                cardScore7 = platinumValue7.value,
                cardScore8 = platinumValue8.value,
                collectionName = R.string.platinum
            )
            CollectionChapter(
                modifier = modifier,
                cardImage1 = R.drawable.epic_1,
                cardImage2 = R.drawable.epic_2,
                cardImage3 = R.drawable.epic_3,
                cardImage4 = R.drawable.epic_4,
                cardImage5 = R.drawable.epic_5,
                cardImage6 = R.drawable.epic_6,
                cardImage7 = R.drawable.epic_7,
                cardImage8 = R.drawable.epic_8,
                cardName1 = R.string.epic1,
                cardName2 = R.string.epic2,
                cardName3 = R.string.epic3,
                cardName4 = R.string.epic4,
                cardName5 = R.string.epic5,
                cardName6 = R.string.epic6,
                cardName7 = R.string.epic7,
                cardName8 = R.string.epic8,
                cardScore1 = epicValue1.value,
                cardScore2 = epicValue2.value,
                cardScore3 = epicValue3.value,
                cardScore4 = epicValue4.value,
                cardScore5 = epicValue5.value,
                cardScore6 = epicValue6.value,
                cardScore7 = epicValue7.value,
                cardScore8 = epicValue8.value,
                collectionName = R.string.epic
            )
        }
    }
    BackHandler {
        navController.navigate(Screens.MainIncome.route)
    }
}