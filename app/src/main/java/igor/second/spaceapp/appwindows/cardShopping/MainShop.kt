package igor.second.spaceapp.appwindows.cardShopping

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.appsettings.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        PurchaseViewModelFactory(LocalContext.current, dataStoreManager)),
    networkViewModel: NetworkViewModel = viewModel()
) {

    val context = LocalContext.current
    val activity = LocalActivity.current
    val products by viewModel.products.collectAsState()
    val purchases by viewModel.purchases.collectAsState()
    val refundNotification by viewModel.showRefundNotification.collectAsState()
    val isOnline by networkViewModel.isOnline.collectAsState()

    LaunchedEffect(Unit) {
        while (true) {
            val connectivityStatus = NetworkUtils.isNetworkAvailable(context)
            networkViewModel.updateNetworkStatus(connectivityStatus)
            delay(5000) // Проверяем каждые 5 секунд
        }
    }

    refundNotification?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.clearRefundNotification()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp, top = 16.dp, start = 8.dp),

    ) {
        if (activity == null) {
            CircularProgressIndicator()
        } else if (!isOnline) {
            Column {
                CircularProgressIndicator()
                Text(
                    text = "Отсутствует интернет-соединение",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = {
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }) {
                    Text("Нажмите чтобы включить")
                }
            }
        } else {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    LazyRow (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                    ) {
                        items(products.filter { it.productId.startsWith("add_epic") }) { product ->
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
                item {
                    LazyRow (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                    ) {
                        items(products.filter { it.productId.startsWith("add_platinum") }) { product ->
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
                item {
                    LazyRow (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
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
                item {
                    LazyRow (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
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
                }
            }
        }
    }
}

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }
}

class NetworkViewModel : ViewModel() {
    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    fun updateNetworkStatus(isConnected: Boolean) {
        _isOnline.value = isConnected
    }
}



