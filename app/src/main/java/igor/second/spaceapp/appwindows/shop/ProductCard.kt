package igor.second.spaceapp.appwindows.shop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.ProductDetails

@Composable
fun ProductCard(
    product: ProductDetails,
    isPurchased: Boolean,
    onPurchaseClick: () -> Unit,
    onRefundClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(184.dp)
            .padding(end = 16.dp),
        border = BorderStroke(1.dp, Color.Red)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = product.oneTimePurchaseOfferDetails?.formattedPrice ?: "",
                style = MaterialTheme.typography.titleMedium
            )

            if (isPurchased) {
                val rewardText = when (product.productId) {
                    "points_pack" -> "Reward: 10 points"
                    "premium_status" -> "Reward: Premium status"
                    else -> ""
                }

                Text(
                    text = rewardText,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

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
                    onClick = onPurchaseClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Purchase")
                }
            }
        }
    }
}