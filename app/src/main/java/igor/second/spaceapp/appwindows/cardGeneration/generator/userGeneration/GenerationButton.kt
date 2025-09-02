package igor.second.spaceapp.appwindows.cardGeneration.generator.userGeneration

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GenerationButton(
    modifier: Modifier = Modifier,
    userGenerationLevel: MutableState<Int>,
    @DrawableRes icon: Int
){

    var value = (1..5).random()

    Box(modifier = modifier){
        Image(
            painterResource(icon),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = {
                        generationButtonFun(
                            userGenerationLevel = userGenerationLevel,
                            value = value
                            )
                    }
                )
        )
        CircularProgressIndicator(
            modifier = modifier.size(64.dp),
            color = when (userGenerationLevel.value){
                in 0..80 -> Color(0xFFE7931D)
                in 81..160 -> Color(0xFFD3CDCA)
                in 161..240 -> Color(0xFFFFE300)
                in 241..320 -> Color(0xFF0091FF)
                in 321..400 -> Color(0xFFFF0000)
                else -> Color(0xFF00FF0D)},
            trackColor = Color.Blue,
            progress = { userGenerationLevel.value / 480.toFloat() }
        )
    }
}