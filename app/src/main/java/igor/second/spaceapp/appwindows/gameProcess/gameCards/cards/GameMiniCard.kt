package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GameMiniCard(
    @DrawableRes image: Int
){
    Card (
        modifier = Modifier
            .size(width = 64.dp, height = 112.dp)
            .padding(end = 8.dp)
            .border(width = 2.dp, color = Color.Red)
    ) {
        Image(
            painterResource(image),
            contentDescription = "box card",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}