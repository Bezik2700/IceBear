package igor.second.spaceapp.appwindows.cardShopping.online

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModel
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModelFactory

@Composable
fun OnlinePurchase(
    dataStoreManager: DataStoreManager,
    viewModel: PurchaseViewModel = viewModel(factory =
        PurchaseViewModelFactory(LocalContext.current, dataStoreManager)
    ),
){

    val activity = LocalActivity.current
    val products by viewModel.products.collectAsState()
    val purchases by viewModel.purchases.collectAsState()

    if (activity == null) {
        CircularProgressIndicator()
    } else {
        Text("Purchase for real money")
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(products.filter { it.productId.startsWith("add_epic") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(products.filter { it.productId.startsWith("add_platinum") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(products.filter { it.productId.startsWith("plus_") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            items(products.filter { it.productId.startsWith("upgrade_") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    }
                )
            }
        }
    }
}