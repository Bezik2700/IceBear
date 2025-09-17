package igor.second.spaceapp.appwindows.cardShopping.offline

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.R

@Composable
fun OfflinePurchaseCard(
    cardWidth: Float,
    onClick: () -> Unit,
    @DrawableRes image: Int,
    @StringRes text: Int,
    price: Int
){
    Card(
        modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth(cardWidth)
            .height(200.dp)
            .clickable(onClick = { onClick.invoke() })
    ) {
        Box(modifier = Modifier) {

            Image(
                painterResource(R.drawable.offline_purchase_fon),
                contentDescription = "fon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(image),
                    contentDescription = "card image",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxHeight(0.6f)
                )
                Text(
                    stringResource(R.string.buy),
                    color = Color.White
                )
                Text(
                    stringResource(text),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Card (modifier = Modifier.fillMaxWidth()) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.money))
                        Text(
                            price.toString(),
                            fontSize = 24.sp,
                        )
                    }
                }
            }
        }
    }
}