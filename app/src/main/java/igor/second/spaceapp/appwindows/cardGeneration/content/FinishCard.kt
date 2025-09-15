package igor.second.spaceapp.appwindows.cardGeneration.content

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FinishCard(
    @DrawableRes image: Int
){
    Card (
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.7f)
            .clip(RoundedCornerShape(32.dp))
    ) {
        Image(
            painterResource(image),
            contentDescription = "finish card",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}