package igor.second.spaceapp.appwindows.cardShopping.online

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.MainViewModel
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModel
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModelFactory

@Composable
fun OnlinePurchase(
    dataStoreManager: DataStoreManager,
    viewModel: PurchaseViewModel = viewModel(factory =
        PurchaseViewModelFactory(LocalContext.current, dataStoreManager)
    ),
    mainViewModel: MainViewModel = viewModel()
){

    val activity = LocalActivity.current
    val products by viewModel.products.collectAsState()
    val purchases by viewModel.purchases.collectAsState()
    val isOnline by mainViewModel.isOnline.collectAsState()

    var dialogEnabled = remember { mutableStateOf(false) }
    var dialogNumber by remember { mutableIntStateOf(0) }

    if (activity == null || !isOnline) {
        CircularProgressIndicator()
    } else {
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                CustomInformationIcon(
                    onClick = {
                        dialogNumber = 1
                        dialogEnabled.value = true
                    }
                )
            }
            items(products.filter { it.productId.startsWith("add_epic") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    },
                    image = when (product.productId){
                        "add_epic_card1" -> R.drawable.epic_1
                        "add_epic_card2" -> R.drawable.epic_2
                        "add_epic_card3" -> R.drawable.epic_3
                        else -> R.drawable.epic_4
                    },
                    text = when (product.productId) {
                        "add_epic_card1" -> R.string.epic_1
                        "add_epic_card2" -> R.string.epic_2
                        "add_epic_card3" -> R.string.epic_3
                        else -> R.string.epic_4
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                CustomInformationIcon(
                    onClick = {
                        dialogNumber = 2
                        dialogEnabled.value = true
                    }
                )
            }
            items(products.filter { it.productId.startsWith("add_platinum") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    },
                    image = when (product.productId){
                        "add_platinum_card1" -> R.drawable.platinum_1
                        "add_platinum_card2" -> R.drawable.platinum_2
                        "add_platinum_card3" -> R.drawable.platinum_3
                        else -> R.drawable.platinum_4
                    },
                    text = when (product.productId) {
                        "add_platinum_card1" -> R.string.platinum_1
                        "add_platinum_card2" -> R.string.platinum_2
                        "add_platinum_card3" -> R.string.platinum_3
                        else -> R.string.platinum_4
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                CustomInformationIcon(
                    onClick = {
                        dialogNumber = 3
                        dialogEnabled.value = true
                    }
                )
            }
            items(products.filter { it.productId.startsWith("plus_") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    },
                    image = when (product.productId){
                        "plus_money_1" -> R.drawable.plus_money_1
                        "plus_money_2" -> R.drawable.plus_money_2
                        "plus_money_3" -> R.drawable.plus_money_3
                        else -> R.drawable.plus_money_4
                    },
                    text = when (product.productId) {
                        "plus_money_1" -> R.string.plus_money_1
                        "plus_money_2" -> R.string.plus_money_2
                        "plus_money_3" -> R.string.plus_money_3
                        else -> R.string.plus_money_4
                    }
                )
            }
        }
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            item {
                CustomInformationIcon(
                    onClick = {
                        dialogNumber = 4
                        dialogEnabled.value = true
                    }
                )
            }
            items(products.filter { it.productId.startsWith("upgrade_") }) { product ->
                OnlinePurchaseCard(
                    product = product,
                    isPurchased = purchases.any { it.products.contains(product.productId) },
                    onPurchaseClick = { viewModel.makePurchase(activity, product) },
                    onRefundClick = {
                        purchases.firstOrNull { it.products.contains(product.productId) }?.let {
                            viewModel.handleRefund(it)
                        }
                    },
                    image = when (product.productId){
                        "upgrade_1" -> R.drawable.upgrade_1
                        "upgrade_2" -> R.drawable.upgrade_2
                        "upgrade_3" -> R.drawable.upgrade_3
                        else -> R.drawable.upgrade_4
                    },
                    text = when (product.productId) {
                        "upgrade_1" -> R.string.upgrade_1
                        "upgrade_2" -> R.string.upgrade_2
                        "upgrade_3" -> R.string.upgrade_3
                        else -> R.string.upgrade_4
                    }
                )
            }
        }
    }
    if (dialogEnabled.value){
        PurchaseInfoDialog(
            enabled = dialogEnabled,
            text =
                when (dialogNumber){
                1 -> R.string.add_epic_info
                2 -> R.string.add_platinum_info
                3 -> R.string.plus_money_info
                else -> R.string.upgrade_info
            },
        )
    }
}