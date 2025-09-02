package igor.second.spaceapp.appwindows.gameProcess.gameCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.GameMiniCard
import igor.second.spaceapp.appwindows.gameProcess.gameCards.logic.cardNumber

@Composable
fun GameCardBox(
    sliderPosition: MutableState<Float>
){
    Box(modifier = Modifier){
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 108.dp, start = 64.dp)
        ) {
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 5))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 6))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 7))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 8))
        }
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp)
        ) {
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 1))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 2))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 3))
            GameMiniCard(cardNumber(sliderPosition = sliderPosition, cardValue = 4))
        }

    }
}