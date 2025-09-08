package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GameMiniCard(
    @DrawableRes image: Int,
    onClick: () -> Unit,
    cardScore: Int
){

    val colorMatrix = ColorMatrix().apply { setToSaturation(0f) }

    Card (
        modifier = Modifier
            .size(width = 64.dp, height = 112.dp)
            .padding(end = 8.dp)
            .border(width = 2.dp, color = Color.Red)
            .clickable(
                enabled = cardScore != 0,
                onClick = { onClick.invoke() }
            )
    ) {
        if (cardScore != 0){
            Image(
                painterResource(image),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painterResource(image),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.colorMatrix(colorMatrix)
            )
        }
    }
}