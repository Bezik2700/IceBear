package igor.second.spaceapp.appwindows.cardShopping.offline

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            .clickable(onClick = { onClick.invoke() }),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush
                        .verticalGradient(
                            colors = listOf(
                                Color(0xFFE56CBE),
                                Color(0xFF6C78E5),
                                Color(0xFF000000)
                            )
                        )
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(image),
                contentDescription = "card image",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxHeight(0.6f)
                    .shadow(4.dp, shape = CircleShape)
            )
            Text(
                stringResource(text),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(color = Color(0xFF9678B6))
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        stringResource(R.string.money),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        price.toString(),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}