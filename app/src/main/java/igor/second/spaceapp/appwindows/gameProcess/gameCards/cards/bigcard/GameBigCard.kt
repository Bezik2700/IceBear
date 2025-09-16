package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.bigcard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.R

@Composable
fun GameBigCard(
    @DrawableRes image: Int,
    text: String
){

    Box(modifier = Modifier.padding(bottom = 16.dp)){
        Card (
            modifier = Modifier
                .rotate(45f)
                .size(width = 140.dp, height = 260.dp)
        ) {
            Image(
                painterResource(R.drawable.flip_side_card),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (
            modifier = Modifier
                .rotate(30f)
                .size(width = 140.dp, height = 260.dp)
        ) {
            Image(
                painterResource(R.drawable.flip_side_card),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (
            modifier = Modifier
                .rotate(15f)
                .size(width = 140.dp, height = 260.dp)
        ) {
            Image(
                painterResource(R.drawable.flip_side_card),
                contentDescription = "box card",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Card (modifier = Modifier.size(width = 140.dp, height = 260.dp)) {
            Box{
                Image(
                    painterResource(image),
                    contentDescription = "box card top",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 8.dp, start = 8.dp)
                )
                Text(
                    text,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(bottom = 8.dp, end = 8.dp)
                )
            }
        }
    }
}
