package igor.second.spaceapp.appwindows.shop

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.settings.DataStoreManager

class PurchaseViewModelFactory(
    private val context: Context,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PurchaseViewModel(context, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun MainShop(
    dataStoreManager: DataStoreManager,
    viewModel: PurchaseViewModel = viewModel(factory =
        PurchaseViewModelFactory(LocalContext.current, dataStoreManager))
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val products by viewModel.products.collectAsState()
    val purchases by viewModel.purchases.collectAsState()
    val refundNotification by viewModel.showRefundNotification.collectAsState()

    refundNotification?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.clearRefundNotification()
        }
    }

    Column(modifier = Modifier.fillMaxSize()
            .padding(bottom = 32.dp, top = 16.dp, start = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (activity == null) {
            CircularProgressIndicator()
        } else {
            LazyRow (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight(0.33f)
            ) {
                items(products.filter { it.productId.startsWith("add_") }) { product ->
                    ProductCard(
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
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                items(products.filter { it.productId.startsWith("upgrade_") }) { product ->
                    ProductCard(
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
                modifier = Modifier.fillMaxHeight()
            ) {
                items(products.filter { it.productId.startsWith("plus_") }) { product ->
                    ProductCard(
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
}

