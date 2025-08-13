package igor.second.spaceapp.appwindows.shop

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.ProductDetails


@Composable
fun ProductCard(
    product: ProductDetails,
    isSelected: Boolean,
    onSelect: () -> Unit,
    viewModel: PurchaseViewModel,
    activity: Activity
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(product.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = product.oneTimePurchaseOfferDetails?.formattedPrice ?: "",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // Кнопка выбора
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onSelect() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для одиночной покупки
            Button(
                onClick = { viewModel.makePurchase(activity, listOf(product)) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buy separately")
            }
        }
    }
}