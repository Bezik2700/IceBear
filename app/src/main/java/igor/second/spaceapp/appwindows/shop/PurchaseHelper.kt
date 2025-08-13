package igor.second.spaceapp.appwindows.shop

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PurchaseViewModel(context: Context) : ViewModel() {
    private val billingClient = BillingClient.newBuilder(context)
        .enablePendingPurchases()
        .setListener { billingResult, purchases ->
            handlePurchases(billingResult, purchases)
        }
        .build()

    // Список всех продуктов
    private val _products = MutableStateFlow<List<ProductDetails>>(emptyList())
    val products: StateFlow<List<ProductDetails>> = _products

    // Выбранные продукты для покупки
    private val _selectedProducts = MutableStateFlow<List<ProductDetails>>(emptyList())
    val selectedProducts: StateFlow<List<ProductDetails>> = _selectedProducts

    private val _status = MutableStateFlow("Initializing...")
    val status: StateFlow<String> = _status

    private val _purchases = MutableStateFlow<List<Purchase>>(emptyList())
    val purchases: StateFlow<List<Purchase>> = _purchases

    init {
        connectToBilling()
    }

    private fun connectToBilling() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    _status.value = "Billing connected"
                    queryProducts()
                    queryPurchases()
                } else {
                    _status.value = "Error: ${billingResult.debugMessage}"
                }
            }

            override fun onBillingServiceDisconnected() {
                _status.value = "Billing disconnected"
            }
        })
    }

    private fun queryProducts() {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("firstshopping")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("secondshopping")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("thirdshopping")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            )
            .build()

        billingClient.queryProductDetailsAsync(params) { _, productDetailsList ->
            _products.value = productDetailsList
        }
    }

    private fun queryPurchases() {
        val purchasesResult = billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult, purchasesList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                _purchases.value = purchasesList
                processPurchases(purchasesList)
            }
        }
    }

    private fun processPurchases(purchases: List<Purchase>) {
        purchases.forEach { purchase ->
            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                acknowledgePurchase(purchase)
            }
        }
    }

    fun toggleProductSelection(product: ProductDetails) {
        val currentSelected = _selectedProducts.value.toMutableList()
        if (currentSelected.contains(product)) {
            currentSelected.remove(product)
        } else {
            currentSelected.add(product)
        }
        _selectedProducts.value = currentSelected
    }

    fun makePurchase(activity: Activity, products: List<ProductDetails>) {
        if (products.isEmpty()) {
            _status.value = "No products selected"
            return
        }

        val productDetailsParamsList = products.map { product ->
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(product)
                .build()
        }

        val params = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

        billingClient.launchBillingFlow(activity, params)
    }

    private fun handlePurchases(billingResult: BillingResult, purchases: List<Purchase>?) {
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                purchases?.let {
                    _purchases.value = it
                    processPurchases(it)
                    _status.value = "Purchase successful"
                    _selectedProducts.value = emptyList() // Очищаем выбор после покупки
                }
            }
            BillingClient.BillingResponseCode.USER_CANCELED -> {
                _status.value = "Purchase canceled"
            }
            else -> {
                _status.value = "Purchase error: ${billingResult.debugMessage}"
            }
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.acknowledgePurchase(params) { billingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                _status.value = "Purchase acknowledged"
            }
        }
    }
}