package igor.second.spaceapp.appwindows.cardShopping.online

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.android.billingclient.api.ProductDetails
import igor.second.spaceapp.R

@Composable
fun OnlinePurchaseCard(
    product: ProductDetails,
    isPurchased: Boolean,
    onPurchaseClick: () -> Unit,
    onRefundClick: () -> Unit,
    @DrawableRes image: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(end = 16.dp)) {
        Card(modifier = modifier
            .width(140.dp)
            .height(240.dp)
        ) {
            Box(modifier = modifier) {
                Image(
                    painterResource(R.drawable.online_purchase_fon),
                    contentDescription = "fon",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(image),
                        contentDescription = "card image",
                        modifier = modifier
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
                    if (isPurchased) {
                        Card (
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable(onClick = onRefundClick)
                        ) {
                            Text("Request Refund")
                        }
                    } else {
                        Card (
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable(onClick = onPurchaseClick)
                        ) {
                            Text(
                                product.oneTimePurchaseOfferDetails?.formattedPrice ?: "",
                                fontSize = 18.sp,
                                modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}