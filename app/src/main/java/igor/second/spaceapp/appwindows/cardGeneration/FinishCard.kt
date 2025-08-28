package igor.second.spaceapp.appwindows.cardGeneration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import igor.second.spaceapp.R

@Composable
fun FinishCard(){
    Card (
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.6f)
            .clickable(onClick = {})
    ) {
        Image(
            painterResource(R.drawable.ic_launcher_background),
            contentDescription = "finish card",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}