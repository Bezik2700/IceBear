package igor.second.spaceapp.appwindows.cardShopping

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.consumePurchase
import igor.second.spaceapp.appsettings.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PurchaseViewModel(
    context: Context,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val billingClient = rememberBillingClient(context)

    private val _products = MutableStateFlow<List<ProductDetails>>(emptyList())
    val products: StateFlow<List<ProductDetails>> = _products.asStateFlow()

    private val _purchases = MutableStateFlow<List<Purchase>>(emptyList())
    val purchases: StateFlow<List<Purchase>> = _purchases.asStateFlow()

    private val _status = MutableStateFlow("Initializing billing...")
    val status: StateFlow<String> = _status.asStateFlow()

    private val _showRefundNotification = MutableStateFlow<String?>(null)
    val showRefundNotification: StateFlow<String?> = _showRefundNotification.asStateFlow()

    init {
        setupBilling()
    }

    private fun rememberBillingClient(context: Context): BillingClient {
        return BillingClient.newBuilder(context)
            .setListener { billingResult, purchases ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    processPurchases(purchases)
                }
            }
            .enablePendingPurchases()
            .build()
    }

    private fun setupBilling() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    _status.value = "Billing ready"
                    queryProducts()
                    queryPurchases()
                } else {
                    _status.value = "Billing error: ${billingResult.debugMessage}"
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
                        .setProductId("upgrade_1")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("upgrade_2")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("upgrade_3")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("upgrade_4")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("plus_money_1")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("plus_money_2")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("plus_money_3")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("plus_money_4")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_platinum_card1")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_platinum_card2")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_platinum_card3")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_platinum_card4")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_epic_card1")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_epic_card2")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_epic_card3")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("add_epic_card4")
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            ).build()

        billingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                _products.value = productDetailsList ?: emptyList()
            }
        }
    }

    private fun queryPurchases() {
        billingClient.queryPurchasesAsync(
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
            when (purchase.purchaseState) {
                Purchase.PurchaseState.PURCHASED -> {
                    if (!purchase.isAcknowledged) {
                        acknowledgePurchase(purchase)
                        handlePurchaseRewards(purchase)
                    }

                    if (isConsumableProduct(purchase.products.firstOrNull())) {
                        viewModelScope.launch { consumePurchase(purchase) }
                    }
                }
                Purchase.PurchaseState.PENDING -> {
                    _status.value = "Purchase pending: ${purchase.products.joinToString()}"
                }
                else -> {}
            }
        }
    }

    private fun isConsumableProduct(productId: String?): Boolean {
        return productId?.startsWith("plus_money_") == true ||
                productId?.startsWith("add_card_") == true ||
                productId?.startsWith("upgrade_") == true
    }

    private suspend fun consumePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.consumePurchase(consumeParams).let { result ->
            if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                _status.value = "Purchase consumed: ${purchase.products.joinToString()}"
                queryPurchases()
            } else {
                _status.value = "Consume error: ${result.billingResult.debugMessage}"
            }
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.acknowledgePurchase(params) { billingResult ->
            if (billingResult.responseCode != BillingClient.BillingResponseCode.OK) {
                _status.value = "Acknowledge error: ${billingResult.debugMessage}"
            }
        }
    }

    private fun handlePurchaseRewards(purchase: Purchase) {
        viewModelScope.launch {
            when (purchase.products.firstOrNull()) {
                "upgrade_1" -> dataStoreManager.upGenerationLevel(10)
                "upgrade_2" -> dataStoreManager.upGenerationLevel(30)
                "upgrade_3" -> dataStoreManager.upGenerationLevel(90)
                "upgrade_4" -> dataStoreManager.upGenerationLevel(150)
                "plus_money_1" -> dataStoreManager.upMoneyValue(15)
                "plus_money_2" -> dataStoreManager.upMoneyValue(45)
                "plus_money_3" -> dataStoreManager.upMoneyValue(110)
                "plus_money_4" -> dataStoreManager.upMoneyValue(250)
                "add_platinum_card1" -> dataStoreManager.plusCardValue(1)
                "add_platinum_card2" -> dataStoreManager.plusCardValue(2)
                "add_platinum_card3" -> dataStoreManager.plusCardValue(3)
                "add_platinum_card4" -> dataStoreManager.plusCardValue(4)
                "add_epic_card1" -> dataStoreManager.plusCardValue(5)
                "add_epic_card2" -> dataStoreManager.plusCardValue(6)
                "add_epic_card3" -> dataStoreManager.plusCardValue(7)
                "add_epic_card4" -> dataStoreManager.plusCardValue(8)
            }
        }
    }

    fun makePurchase(activity: Activity, product: ProductDetails) {
        val params = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(product)
                        .build()
                )
            )
            .build()

        val billingResult = billingClient.launchBillingFlow(activity, params)
        if (billingResult.responseCode != BillingClient.BillingResponseCode.OK) {
            _status.value = "Purchase error: ${billingResult.debugMessage}"
        }
    }

    fun handleRefund(purchase: Purchase) {
        viewModelScope.launch {
            when (purchase.products.firstOrNull()) {
                "upgrade_1" -> dataStoreManager.removeGenerationLevel(10)
                "upgrade_2" -> dataStoreManager.removeGenerationLevel(30)
                "upgrade_3" -> dataStoreManager.removeGenerationLevel(90)
                "upgrade_4" -> dataStoreManager.removeGenerationLevel(150)
                "plus_money_1" -> dataStoreManager.removeMoneyValue(15)
                "plus_money_2" -> dataStoreManager.removeMoneyValue(45)
                "plus_money_3" -> dataStoreManager.removeMoneyValue(110)
                "plus_money_4" -> dataStoreManager.removeMoneyValue(250)
                "add_platinum_card1" -> dataStoreManager.removeCardValue(1)
                "add_platinum_card2" -> dataStoreManager.removeCardValue(2)
                "add_platinum_card3" -> dataStoreManager.removeCardValue(3)
                "add_platinum_card4" -> dataStoreManager.removeCardValue(4)
                "add_epic_card1" -> dataStoreManager.removeCardValue(5)
                "add_epic_card2" -> dataStoreManager.removeCardValue(6)
                "add_epic_card3" -> dataStoreManager.removeCardValue(7)
                "add_epic_card4" -> dataStoreManager.removeCardValue(8)
            }

            if (isConsumableProduct(purchase.products.firstOrNull())) {
                consumePurchase(purchase)
            }

            _showRefundNotification.value = "Refund processed for ${purchase.products.joinToString()}"

            queryPurchases()
        }
    }
    fun clearRefundNotification() {
        _showRefundNotification.value = null
    }
}

