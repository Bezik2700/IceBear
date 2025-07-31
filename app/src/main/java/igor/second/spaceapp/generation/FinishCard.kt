package igor.second.spaceapp.generation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R

@Composable
fun FinishCard(){
    Card (
        modifier = Modifier
            .size(width = 240.dp, height = 480.dp)
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