package igor.second.spaceapp.appwindows.cardCollection

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CollectionChapter(
    modifier: Modifier = Modifier,
    cardImage1: Int,
    cardImage2: Int,
    cardImage3: Int,
    cardImage4: Int,
    cardImage5: Int,
    cardImage6: Int,
    cardImage7: Int,
    cardImage8: Int,
    cardName1: Int,
    cardName2: Int,
    cardName3: Int,
    cardName4: Int,
    cardName5: Int,
    cardName6: Int,
    cardName7: Int,
    cardName8: Int,
    cardScore1: Int,
    cardScore2: Int,
    cardScore3: Int,
    cardScore4: Int,
    cardScore5: Int,
    cardScore6: Int,
    cardScore7: Int,
    cardScore8: Int,
    @StringRes collectionName: Int,
    nameColor: Color
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Card (modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = nameColor)
            ) {
                val text = stringResource(collectionName)
                var animatedText by remember { mutableStateOf("") }

                LaunchedEffect(text) {
                    text.forEachIndexed { index, _ ->
                        delay(100)
                        animatedText = text.take(index + 1)
                    }
                }

                Text(
                    text = animatedText,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(start = 16.dp),
                    color = Color.Black,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.3f),
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier.fillMaxWidth()
        ) {
            CollectionSmallCard(
                cardImage = cardImage1,
                cardName = cardName1,
                cardScore = cardScore1
            )
            CollectionSmallCard(
                cardImage = cardImage2,
                cardName = cardName2,
                cardScore = cardScore2
            )
            CollectionSmallCard(
                cardImage = cardImage3,
                cardName = cardName3,
                cardScore = cardScore3
            )
            CollectionSmallCard(
                cardImage = cardImage4,
                cardName = cardName4,
                cardScore = cardScore4
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            CollectionSmallCard(
                cardImage = cardImage5,
                cardName = cardName5,
                cardScore = cardScore5
            )
            CollectionSmallCard(
                cardImage = cardImage6,
                cardName = cardName6,
                cardScore = cardScore6
            )
            CollectionSmallCard(
                cardImage = cardImage7,
                cardName = cardName7,
                cardScore = cardScore7
            )
            CollectionSmallCard(
                cardImage = cardImage8,
                cardName = cardName8,
                cardScore = cardScore8
            )
        }
    }
}