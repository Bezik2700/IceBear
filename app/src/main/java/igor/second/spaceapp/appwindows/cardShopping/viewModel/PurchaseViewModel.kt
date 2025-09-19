package igor.second.spaceapp.appwindows.cardShopping.viewModel

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
    // Константы для идентификаторов товаров
    companion object {
        const val UPGRADE_1 = "upgrade_1"
        const val UPGRADE_2 = "upgrade_2"
        const val UPGRADE_3 = "upgrade_3"
        const val UPGRADE_4 = "upgrade_4"
        const val PLUS_MONEY_1 = "plus_money_1"
        const val PLUS_MONEY_2 = "plus_money_2"
        const val PLUS_MONEY_3 = "plus_money_3"
        const val PLUS_MONEY_4 = "plus_money_4"
        const val ADD_PLATINUM_CARD1 = "add_platinum_card1"
        const val ADD_PLATINUM_CARD2 = "add_platinum_card2"
        const val ADD_PLATINUM_CARD3 = "add_platinum_card3"
        const val ADD_PLATINUM_CARD4 = "add_platinum_card4"
        const val ADD_EPIC_CARD1 = "add_epic_card1"
        const val ADD_EPIC_CARD2 = "add_epic_card2"
        const val ADD_EPIC_CARD3 = "add_epic_card3"
        const val ADD_EPIC_CARD4 = "add_epic_card4"
    }

    private val billingClient = createBillingClient(context)

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

    private fun createBillingClient(context: Context): BillingClient {
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
                        .setProductId(UPGRADE_1)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(UPGRADE_2)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(UPGRADE_3)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(UPGRADE_4)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PLUS_MONEY_1)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PLUS_MONEY_2)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PLUS_MONEY_3)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PLUS_MONEY_4)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_PLATINUM_CARD1)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_PLATINUM_CARD2)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_PLATINUM_CARD3)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_PLATINUM_CARD4)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_EPIC_CARD1)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_EPIC_CARD2)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_EPIC_CARD3)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(ADD_EPIC_CARD4)
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
                        if (isConsumableProduct(purchase.products.firstOrNull())) {
                            // Для потребляемых товаров - потребляем
                            viewModelScope.launch {
                                consumePurchase(purchase)
                            }
                        } else {
                            // Для непотребляемых - подтверждаем
                            acknowledgePurchase(purchase)
                        }
                        handlePurchaseRewards(purchase)
                    }
                }
                Purchase.PurchaseState.PENDING -> {
                    _status.value = "Purchase pending: ${purchase.products.joinToString()}"
                }
                else -> {}
            }
        }
    }

    fun isConsumableProduct(productId: String?): Boolean {
        return productId?.startsWith("upgrade_") != true
    }

    fun isProductPurchased(productId: String): Boolean {
        return if (isConsumableProduct(productId)) {
            false
        } else {
            _purchases.value.any {
                it.products.contains(productId) &&
                        it.purchaseState == Purchase.PurchaseState.PURCHASED
            }
        }
    }

    private suspend fun consumePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.consumePurchase(consumeParams).let { result ->
            if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                _status.value = "Purchase consumed: ${purchase.products.joinToString()}"
                _purchases.value = _purchases.value.filter { it.purchaseToken != purchase.purchaseToken }
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
                UPGRADE_1 -> dataStoreManager.upGenerationLevel(1)
                UPGRADE_2 -> dataStoreManager.upGenerationLevel(3)
                UPGRADE_3 -> dataStoreManager.upGenerationLevel(7)
                UPGRADE_4 -> dataStoreManager.upGenerationLevel(19)
                PLUS_MONEY_1 -> dataStoreManager.upMoneyValue(1000)
                PLUS_MONEY_2 -> dataStoreManager.upMoneyValue(4900)
                PLUS_MONEY_3 -> dataStoreManager.upMoneyValue(9900)
                PLUS_MONEY_4 -> dataStoreManager.upMoneyValue(99900)
                ADD_PLATINUM_CARD1 -> dataStoreManager.plusCardValue(1)
                ADD_PLATINUM_CARD2 -> dataStoreManager.plusCardValue(2)
                ADD_PLATINUM_CARD3 -> dataStoreManager.plusCardValue(3)
                ADD_PLATINUM_CARD4 -> dataStoreManager.plusCardValue(4)
                ADD_EPIC_CARD1 -> dataStoreManager.plusCardValue(5)
                ADD_EPIC_CARD2 -> dataStoreManager.plusCardValue(6)
                ADD_EPIC_CARD3 -> dataStoreManager.plusCardValue(7)
                ADD_EPIC_CARD4 -> dataStoreManager.plusCardValue(8)
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
                UPGRADE_1 -> dataStoreManager.removeGenerationLevel(1)
                UPGRADE_2 -> dataStoreManager.removeGenerationLevel(3)
                UPGRADE_3 -> dataStoreManager.removeGenerationLevel(7)
                UPGRADE_4 -> dataStoreManager.removeGenerationLevel(19)
                PLUS_MONEY_1 -> dataStoreManager.removeMoneyValue(1000)
                PLUS_MONEY_2 -> dataStoreManager.removeMoneyValue(4900)
                PLUS_MONEY_3 -> dataStoreManager.removeMoneyValue(9900)
                PLUS_MONEY_4 -> dataStoreManager.removeMoneyValue(99900)
                ADD_PLATINUM_CARD1 -> dataStoreManager.removeCardValue(1)
                ADD_PLATINUM_CARD2 -> dataStoreManager.removeCardValue(2)
                ADD_PLATINUM_CARD3 -> dataStoreManager.removeCardValue(3)
                ADD_PLATINUM_CARD4 -> dataStoreManager.removeCardValue(4)
                ADD_EPIC_CARD1 -> dataStoreManager.removeCardValue(5)
                ADD_EPIC_CARD2 -> dataStoreManager.removeCardValue(6)
                ADD_EPIC_CARD3 -> dataStoreManager.removeCardValue(7)
                ADD_EPIC_CARD4 -> dataStoreManager.removeCardValue(8)
            }

            _showRefundNotification.value = "Refund processed for ${purchase.products.joinToString()}"
            queryPurchases()
        }
    }

    fun clearRefundNotification() {
        _showRefundNotification.value = null
    }
}