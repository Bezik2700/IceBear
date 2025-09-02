package igor.second.spaceapp.appwindows.gameProcess

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.appwindows.gameProcess.gameCards.GameCardBox
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.ChatCards
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.CustomSlider
import igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.GameBigCard

@Composable
fun MainConnection(
    modifier: Modifier = Modifier
){

    var sliderPosition = remember { mutableFloatStateOf(1f) }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 120.dp, top = 80.dp)
    ) {
        GameBigCard()
        ChatCards()
        GameCardBox(sliderPosition = sliderPosition)
        CustomSlider(sliderPosition = sliderPosition)
    }
}

@Preview
@Composable
fun GameCardPreview(){
    MainConnection()
}