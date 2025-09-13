package igor.second.spaceapp.appwindows.cardShopping

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.MainViewModel
import igor.second.spaceapp.appsettings.network.IsNotOnlineDialog
import igor.second.spaceapp.appsettings.network.NetworkUtils
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardShopping.offline.OfflinePurchase
import igor.second.spaceapp.appwindows.cardShopping.online.OnlinePurchase
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModel
import igor.second.spaceapp.appwindows.cardShopping.viewModel.PurchaseViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun MainShop(
    dataStoreManager: DataStoreManager,
    viewModel: PurchaseViewModel = viewModel(factory =
        PurchaseViewModelFactory(LocalContext.current, dataStoreManager)),
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val refundNotification by viewModel.showRefundNotification.collectAsState()
    val isOnline by mainViewModel.isOnline.collectAsState()

    LaunchedEffect(Unit) {
        while (true) {
            val connectivityStatus = NetworkUtils.isNetworkAvailable(context)
            mainViewModel.updateNetworkStatus(connectivityStatus)
            delay(5000)
        }
    }

    refundNotification?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.clearRefundNotification()
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 80.dp, start = 8.dp),

    ) {
        item {
            OfflinePurchase()
            if (!isOnline){
                IsNotOnlineDialog(context = context)
            } else {
                OnlinePurchase(dataStoreManager = dataStoreManager, viewModel = viewModel())
            }
        }
    }
    BackHandler {
        navController.navigate(Screens.MainIncome.route)
    }
}



