package igor.second.spaceapp.appwindows.cardShopping.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import igor.second.spaceapp.appsettings.DataStoreManager

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