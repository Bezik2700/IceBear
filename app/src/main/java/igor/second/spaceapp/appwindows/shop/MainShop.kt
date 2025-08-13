package igor.second.spaceapp.appwindows.shop

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainShop() {

    val context = LocalContext.current
    val activity: Activity? = LocalActivity.current
    val viewModel: PurchaseViewModel = viewModel { PurchaseViewModel(context) }
    val products by viewModel.products.collectAsState()
    val selectedProducts by viewModel.selectedProducts.collectAsState()
    val status by viewModel.status.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "In-App Purchases",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(status, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(24.dp))

        if (activity == null) {
            Text("Activity not available")
        } else {
            // Кнопка для покупки выбранных товаров
            if (selectedProducts.isNotEmpty()) {
                Button(
                    onClick = { viewModel.makePurchase(activity, selectedProducts) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Buy ${selectedProducts.size} selected items")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        isSelected = selectedProducts.contains(product),
                        onSelect = { viewModel.toggleProductSelection(product) },
                        viewModel = viewModel,
                        activity = activity
                    )
                }
            }
        }
    }
}