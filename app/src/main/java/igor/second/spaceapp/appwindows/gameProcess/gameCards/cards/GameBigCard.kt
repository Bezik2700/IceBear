package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R

@Composable
fun GameBigCard(){
    Box(modifier = Modifier.padding(bottom = 16.dp)){
        Card (
            modifier = Modifier
                .rotate(45f)
                .size(width = 160.dp, height = 320.dp)
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (
            modifier = Modifier
                .rotate(30f)
                .size(width = 160.dp, height = 320.dp)
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (
            modifier = Modifier
                .rotate(15f)
                .size(width = 160.dp, height = 320.dp)
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (
            modifier = Modifier
                .size(width = 160.dp, height = 320.dp)
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
