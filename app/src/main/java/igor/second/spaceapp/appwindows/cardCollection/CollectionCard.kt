package igor.second.spaceapp.appwindows.cardCollection

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import igor.second.spaceapp.R

@Composable
fun CollectionSmallCard(
    modifier: Modifier = Modifier,
    cardScore: Int,
    @DrawableRes cardImage: Int,
    @StringRes cardName: Int
){

    var enabled by remember { mutableStateOf(false) }

    if (enabled){
        Dialog(
            onDismissRequest = {enabled = !enabled}
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    IconButton(
                        onClick = {enabled = !enabled}
                    ) {
                        Icon(
                            Icons.Rounded.Close,
                            tint = Color.White,
                            contentDescription = "exit"
                        )
                    }
                }
                Card (modifier = modifier
                    .size(width = 280.dp, height = 560.dp)
                ) {
                    Image(
                        painterResource(cardImage),
                        contentDescription = "box card",
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    } else {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(cardName))
            Card (
                modifier = Modifier
                    .size(width = 64.dp, height = 128.dp)
                    .clickable(onClick = {enabled = !enabled})
            ) {
                Image(
                    painterResource(cardImage),
                    contentDescription = "box card",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                cardScore.toString(),
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun CollectionSmallCardPreview(){
    CollectionSmallCard(
        cardScore = 0,
        cardImage = R.drawable.bronze_1,
        cardName = R.string.bronze1
    )
}