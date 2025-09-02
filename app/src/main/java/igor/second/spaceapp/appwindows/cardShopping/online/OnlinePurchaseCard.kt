package igor.second.spaceapp.appwindows.cardShopping.online

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.ProductDetails

@Composable
fun OnlinePurchaseCard(
    product: ProductDetails,
    isPurchased: Boolean,
    onPurchaseClick: () -> Unit,
    onRefundClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(end = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = product.name)
            Text(text = product.description)
            Text(text = product.oneTimePurchaseOfferDetails?.formattedPrice ?: "")

            if (isPurchased) {
                val rewardText = when (product.productId) {
                    "points_pack" -> "Reward: 10 points"
                    "premium_status" -> "Reward: Premium status"
                    else -> ""
                }
                Text(text = rewardText,)

                Button(
                    onClick = onRefundClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Request Refund")
                }
            } else {
                Button(
                    onClick = onPurchaseClick
                ) {
                    Text("Purchase")
                }
            }
        }
    }
}