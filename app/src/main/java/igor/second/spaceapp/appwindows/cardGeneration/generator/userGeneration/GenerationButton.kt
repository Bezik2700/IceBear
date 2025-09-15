package igor.second.spaceapp.appwindows.cardGeneration.generator.userGeneration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GenerationButton(
    modifier: Modifier = Modifier,
    generationValue: MutableState<Int>,
    userGenerationLevel: MutableState<Int>,
    color: Color
){

    var value = 0

    value = when(userGenerationLevel.value){
        0 -> (1..5).random()
        in 1..2 -> (2..6).random()
        in 3..6 -> (3..7).random()
        in 7..18 -> (4..8).random()
        else -> (5..9).random()
    }

    Box(modifier = modifier){
        Card(
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = {
                        generationButtonFun(
                            generationValue = generationValue,
                            value = value
                        )
                    }
                )
        ){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = color)
            ) {
                Text("+ $value")
            }
        }
        CircularProgressIndicator(
            modifier = modifier.size(64.dp),
            color = when (generationValue.value){
                in 0..80 -> Color(0xFFE7931D)
                in 81..160 -> Color(0xFFD3CDCA)
                in 161..240 -> Color(0xFFFFE300)
                in 241..320 -> Color(0xFF0091FF)
                in 321..400 -> Color(0xFFFF0000)
                else -> Color(0xFF00FF0D)},
            trackColor = Color.White,
            progress = { generationValue.value / 480.toFloat() }
        )
    }
}