package igor.second.spaceapp.appwindows.cardShopping.offline

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager

@Composable
fun OfflinePurchase(
    context: Context,
    userGenerationLevel: MutableState<Int>,
    userName: MutableState<String>,
    userMoneyValue: MutableState<Int>,
    dataStoreManager: DataStoreManager,
    bronzeValue1: MutableState<Int>,
    bronzeValue2: MutableState<Int>,
    bronzeValue3: MutableState<Int>,
    bronzeValue4: MutableState<Int>,
    bronzeValue5: MutableState<Int>,
    bronzeValue6: MutableState<Int>,
    bronzeValue7: MutableState<Int>,
    bronzeValue8: MutableState<Int>,
    silverValue1: MutableState<Int>,
    silverValue2: MutableState<Int>,
    silverValue3: MutableState<Int>,
    silverValue4: MutableState<Int>,
    silverValue5: MutableState<Int>,
    silverValue6: MutableState<Int>,
    silverValue7: MutableState<Int>,
    silverValue8: MutableState<Int>,
    goldValue1: MutableState<Int>,
    goldValue2: MutableState<Int>,
    goldValue3: MutableState<Int>,
    goldValue4: MutableState<Int>,
    goldValue5: MutableState<Int>,
    goldValue6: MutableState<Int>,
    goldValue7: MutableState<Int>,
    goldValue8: MutableState<Int>,
    diamondValue1: MutableState<Int>,
    diamondValue2: MutableState<Int>,
    diamondValue3: MutableState<Int>,
    diamondValue4: MutableState<Int>,
    diamondValue5: MutableState<Int>,
    diamondValue6: MutableState<Int>,
    diamondValue7: MutableState<Int>,
    diamondValue8: MutableState<Int>,
    platinumValue1: MutableState<Int>,
    platinumValue2: MutableState<Int>,
    platinumValue3: MutableState<Int>,
    platinumValue4: MutableState<Int>,
    platinumValue5: MutableState<Int>,
    platinumValue6: MutableState<Int>,
    platinumValue7: MutableState<Int>,
    platinumValue8: MutableState<Int>,
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    epicValue5: MutableState<Int>,
    epicValue6: MutableState<Int>,
    epicValue7: MutableState<Int>,
    epicValue8: MutableState<Int>
){

    val price_1 by remember { mutableIntStateOf(100) }
    val price_2 by remember { mutableIntStateOf(200) }
    val price_3 by remember { mutableIntStateOf(400) }
    val price_4 by remember { mutableIntStateOf(500) }
    val price_5 by remember { mutableIntStateOf(900) }
    val price_6 by remember { mutableIntStateOf(1000) }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp, start = 8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            OfflinePurchaseCard(
                cardWidth = 0.3f,
                onClick = {
                    if (userMoneyValue.value >= price_1){
                        userMoneyValue.value -= price_1
                        offlinePurchaseFun(
                            bronzeValue1 = bronzeValue1,
                            bronzeValue2 = bronzeValue2,
                            bronzeValue3 = bronzeValue3,
                            bronzeValue4 = bronzeValue4,
                            bronzeValue5 = bronzeValue5,
                            bronzeValue6 = bronzeValue6,
                            bronzeValue7 = bronzeValue7,
                            bronzeValue8 = bronzeValue8,
                            silverValue1 = silverValue1,
                            silverValue2 = silverValue2,
                            silverValue3 = silverValue3,
                            silverValue4 = silverValue4,
                            silverValue5 = silverValue5,
                            silverValue6 = silverValue6,
                            silverValue7 = silverValue7,
                            silverValue8 = silverValue8,
                            goldValue1 = goldValue1,
                            goldValue2 = goldValue2,
                            goldValue3 = goldValue3,
                            goldValue4 = goldValue4,
                            goldValue5 = goldValue5,
                            goldValue6 = goldValue6,
                            goldValue7 = goldValue7,
                            goldValue8 = goldValue8,
                            diamondValue1 = diamondValue1,
                            diamondValue2 = diamondValue2,
                            diamondValue3 = diamondValue3,
                            diamondValue4 = diamondValue4,
                            diamondValue5 = diamondValue5,
                            diamondValue6 = diamondValue6,
                            diamondValue7 = diamondValue7,
                            diamondValue8 = diamondValue8,
                            platinumValue1 = platinumValue1,
                            platinumValue2 = platinumValue2,
                            platinumValue3 = platinumValue3,
                            platinumValue4 = platinumValue4,
                            platinumValue5 = platinumValue5,
                            platinumValue6 = platinumValue6,
                            platinumValue7 = platinumValue7,
                            platinumValue8 = platinumValue8,
                            epicValue1 = epicValue1,
                            epicValue2 = epicValue2,
                            epicValue3 = epicValue3,
                            epicValue4 = epicValue4,
                            epicValue5 = epicValue5,
                            epicValue6 = epicValue6,
                            epicValue7 = epicValue7,
                            epicValue8 = epicValue8,
                            userGenerationLevel = userGenerationLevel,
                            userName = userName,
                            userMoneyValue = userMoneyValue,
                            dataStoreManager = dataStoreManager,
                            purchaseValue = 1
                        )
                    } else {
                        Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                    }
                },
                image = R.drawable.gold_1,
                text = R.string.gold_1,
                price = price_1
            )
            OfflinePurchaseCard(
                cardWidth = 0.5f,
                onClick = {
                    if (userMoneyValue.value >= price_3){
                        userMoneyValue.value -= price_3
                        offlinePurchaseFun(
                            bronzeValue1 = bronzeValue1,
                            bronzeValue2 = bronzeValue2,
                            bronzeValue3 = bronzeValue3,
                            bronzeValue4 = bronzeValue4,
                            bronzeValue5 = bronzeValue5,
                            bronzeValue6 = bronzeValue6,
                            bronzeValue7 = bronzeValue7,
                            bronzeValue8 = bronzeValue8,
                            silverValue1 = silverValue1,
                            silverValue2 = silverValue2,
                            silverValue3 = silverValue3,
                            silverValue4 = silverValue4,
                            silverValue5 = silverValue5,
                            silverValue6 = silverValue6,
                            silverValue7 = silverValue7,
                            silverValue8 = silverValue8,
                            goldValue1 = goldValue1,
                            goldValue2 = goldValue2,
                            goldValue3 = goldValue3,
                            goldValue4 = goldValue4,
                            goldValue5 = goldValue5,
                            goldValue6 = goldValue6,
                            goldValue7 = goldValue7,
                            goldValue8 = goldValue8,
                            diamondValue1 = diamondValue1,
                            diamondValue2 = diamondValue2,
                            diamondValue3 = diamondValue3,
                            diamondValue4 = diamondValue4,
                            diamondValue5 = diamondValue5,
                            diamondValue6 = diamondValue6,
                            diamondValue7 = diamondValue7,
                            diamondValue8 = diamondValue8,
                            platinumValue1 = platinumValue1,
                            platinumValue2 = platinumValue2,
                            platinumValue3 = platinumValue3,
                            platinumValue4 = platinumValue4,
                            platinumValue5 = platinumValue5,
                            platinumValue6 = platinumValue6,
                            platinumValue7 = platinumValue7,
                            platinumValue8 = platinumValue8,
                            epicValue1 = epicValue1,
                            epicValue2 = epicValue2,
                            epicValue3 = epicValue3,
                            epicValue4 = epicValue4,
                            epicValue5 = epicValue5,
                            epicValue6 = epicValue6,
                            epicValue7 = epicValue7,
                            epicValue8 = epicValue8,
                            userGenerationLevel = userGenerationLevel,
                            userName = userName,
                            userMoneyValue = userMoneyValue,
                            dataStoreManager = dataStoreManager,
                            purchaseValue = 3
                        )
                    } else {
                        Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                    }
                },
                image = R.drawable.diamond_1,
                text = R.string.diamond_1,
                price = price_3
            )
            OfflinePurchaseCard(
                cardWidth = 1f,
                onClick = {
                    if (userMoneyValue.value >= price_5){
                        userMoneyValue.value -= price_5
                        offlinePurchaseFun(
                        bronzeValue1 = bronzeValue1,
                        bronzeValue2 = bronzeValue2,
                        bronzeValue3 = bronzeValue3,
                        bronzeValue4 = bronzeValue4,
                        bronzeValue5 = bronzeValue5,
                        bronzeValue6 = bronzeValue6,
                        bronzeValue7 = bronzeValue7,
                        bronzeValue8 = bronzeValue8,
                        silverValue1 = silverValue1,
                        silverValue2 = silverValue2,
                        silverValue3 = silverValue3,
                        silverValue4 = silverValue4,
                        silverValue5 = silverValue5,
                        silverValue6 = silverValue6,
                        silverValue7 = silverValue7,
                        silverValue8 = silverValue8,
                        goldValue1 = goldValue1,
                        goldValue2 = goldValue2,
                        goldValue3 = goldValue3,
                        goldValue4 = goldValue4,
                        goldValue5 = goldValue5,
                        goldValue6 = goldValue6,
                        goldValue7 = goldValue7,
                        goldValue8 = goldValue8,
                        diamondValue1 = diamondValue1,
                        diamondValue2 = diamondValue2,
                        diamondValue3 = diamondValue3,
                        diamondValue4 = diamondValue4,
                        diamondValue5 = diamondValue5,
                        diamondValue6 = diamondValue6,
                        diamondValue7 = diamondValue7,
                        diamondValue8 = diamondValue8,
                        platinumValue1 = platinumValue1,
                        platinumValue2 = platinumValue2,
                        platinumValue3 = platinumValue3,
                        platinumValue4 = platinumValue4,
                        platinumValue5 = platinumValue5,
                        platinumValue6 = platinumValue6,
                        platinumValue7 = platinumValue7,
                        platinumValue8 = platinumValue8,
                        epicValue1 = epicValue1,
                        epicValue2 = epicValue2,
                        epicValue3 = epicValue3,
                        epicValue4 = epicValue4,
                        epicValue5 = epicValue5,
                        epicValue6 = epicValue6,
                        epicValue7 = epicValue7,
                        epicValue8 = epicValue8,
                        userGenerationLevel = userGenerationLevel,
                        userName = userName,
                        userMoneyValue = userMoneyValue,
                        dataStoreManager = dataStoreManager,
                        purchaseValue = 5
                    )
                } else {
                    Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                }},
                image = R.drawable.platinum_1,
                text = R.string.platinum_1,
                price = price_5
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            OfflinePurchaseCard(
                cardWidth = 0.3f,
                onClick = {
                    if (userMoneyValue.value >= price_2){
                        userMoneyValue.value -= price_2
                        offlinePurchaseFun(
                            bronzeValue1 = bronzeValue1,
                            bronzeValue2 = bronzeValue2,
                            bronzeValue3 = bronzeValue3,
                            bronzeValue4 = bronzeValue4,
                            bronzeValue5 = bronzeValue5,
                            bronzeValue6 = bronzeValue6,
                            bronzeValue7 = bronzeValue7,
                            bronzeValue8 = bronzeValue8,
                            silverValue1 = silverValue1,
                            silverValue2 = silverValue2,
                            silverValue3 = silverValue3,
                            silverValue4 = silverValue4,
                            silverValue5 = silverValue5,
                            silverValue6 = silverValue6,
                            silverValue7 = silverValue7,
                            silverValue8 = silverValue8,
                            goldValue1 = goldValue1,
                            goldValue2 = goldValue2,
                            goldValue3 = goldValue3,
                            goldValue4 = goldValue4,
                            goldValue5 = goldValue5,
                            goldValue6 = goldValue6,
                            goldValue7 = goldValue7,
                            goldValue8 = goldValue8,
                            diamondValue1 = diamondValue1,
                            diamondValue2 = diamondValue2,
                            diamondValue3 = diamondValue3,
                            diamondValue4 = diamondValue4,
                            diamondValue5 = diamondValue5,
                            diamondValue6 = diamondValue6,
                            diamondValue7 = diamondValue7,
                            diamondValue8 = diamondValue8,
                            platinumValue1 = platinumValue1,
                            platinumValue2 = platinumValue2,
                            platinumValue3 = platinumValue3,
                            platinumValue4 = platinumValue4,
                            platinumValue5 = platinumValue5,
                            platinumValue6 = platinumValue6,
                            platinumValue7 = platinumValue7,
                            platinumValue8 = platinumValue8,
                            epicValue1 = epicValue1,
                            epicValue2 = epicValue2,
                            epicValue3 = epicValue3,
                            epicValue4 = epicValue4,
                            epicValue5 = epicValue5,
                            epicValue6 = epicValue6,
                            epicValue7 = epicValue7,
                            epicValue8 = epicValue8,
                            userGenerationLevel = userGenerationLevel,
                            userName = userName,
                            userMoneyValue = userMoneyValue,
                            dataStoreManager = dataStoreManager,
                            purchaseValue = 2
                        )
                    } else {
                        Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                    }
                },
                image = R.drawable.gold_2,
                text = R.string.gold_2,
                price = price_2
            )
            OfflinePurchaseCard(
                cardWidth = 0.5f,
                onClick = {
                    if (userMoneyValue.value >= price_4){
                        userMoneyValue.value -= price_4
                        offlinePurchaseFun(
                            bronzeValue1 = bronzeValue1,
                            bronzeValue2 = bronzeValue2,
                            bronzeValue3 = bronzeValue3,
                            bronzeValue4 = bronzeValue4,
                            bronzeValue5 = bronzeValue5,
                            bronzeValue6 = bronzeValue6,
                            bronzeValue7 = bronzeValue7,
                            bronzeValue8 = bronzeValue8,
                            silverValue1 = silverValue1,
                            silverValue2 = silverValue2,
                            silverValue3 = silverValue3,
                            silverValue4 = silverValue4,
                            silverValue5 = silverValue5,
                            silverValue6 = silverValue6,
                            silverValue7 = silverValue7,
                            silverValue8 = silverValue8,
                            goldValue1 = goldValue1,
                            goldValue2 = goldValue2,
                            goldValue3 = goldValue3,
                            goldValue4 = goldValue4,
                            goldValue5 = goldValue5,
                            goldValue6 = goldValue6,
                            goldValue7 = goldValue7,
                            goldValue8 = goldValue8,
                            diamondValue1 = diamondValue1,
                            diamondValue2 = diamondValue2,
                            diamondValue3 = diamondValue3,
                            diamondValue4 = diamondValue4,
                            diamondValue5 = diamondValue5,
                            diamondValue6 = diamondValue6,
                            diamondValue7 = diamondValue7,
                            diamondValue8 = diamondValue8,
                            platinumValue1 = platinumValue1,
                            platinumValue2 = platinumValue2,
                            platinumValue3 = platinumValue3,
                            platinumValue4 = platinumValue4,
                            platinumValue5 = platinumValue5,
                            platinumValue6 = platinumValue6,
                            platinumValue7 = platinumValue7,
                            platinumValue8 = platinumValue8,
                            epicValue1 = epicValue1,
                            epicValue2 = epicValue2,
                            epicValue3 = epicValue3,
                            epicValue4 = epicValue4,
                            epicValue5 = epicValue5,
                            epicValue6 = epicValue6,
                            epicValue7 = epicValue7,
                            epicValue8 = epicValue8,
                            userGenerationLevel = userGenerationLevel,
                            userName = userName,
                            userMoneyValue = userMoneyValue,
                            dataStoreManager = dataStoreManager,
                            purchaseValue = 4
                        )
                    } else {
                        Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                    }
                },
                image = R.drawable.diamond_2,
                text = R.string.diamond_2,
                price = price_4
            )
            OfflinePurchaseCard(
                cardWidth = 1f,
                onClick = {
                    if (userMoneyValue.value >= price_6){
                        userMoneyValue.value -= price_6
                        offlinePurchaseFun(
                            bronzeValue1 = bronzeValue1,
                            bronzeValue2 = bronzeValue2,
                            bronzeValue3 = bronzeValue3,
                            bronzeValue4 = bronzeValue4,
                            bronzeValue5 = bronzeValue5,
                            bronzeValue6 = bronzeValue6,
                            bronzeValue7 = bronzeValue7,
                            bronzeValue8 = bronzeValue8,
                            silverValue1 = silverValue1,
                            silverValue2 = silverValue2,
                            silverValue3 = silverValue3,
                            silverValue4 = silverValue4,
                            silverValue5 = silverValue5,
                            silverValue6 = silverValue6,
                            silverValue7 = silverValue7,
                            silverValue8 = silverValue8,
                            goldValue1 = goldValue1,
                            goldValue2 = goldValue2,
                            goldValue3 = goldValue3,
                            goldValue4 = goldValue4,
                            goldValue5 = goldValue5,
                            goldValue6 = goldValue6,
                            goldValue7 = goldValue7,
                            goldValue8 = goldValue8,
                            diamondValue1 = diamondValue1,
                            diamondValue2 = diamondValue2,
                            diamondValue3 = diamondValue3,
                            diamondValue4 = diamondValue4,
                            diamondValue5 = diamondValue5,
                            diamondValue6 = diamondValue6,
                            diamondValue7 = diamondValue7,
                            diamondValue8 = diamondValue8,
                            platinumValue1 = platinumValue1,
                            platinumValue2 = platinumValue2,
                            platinumValue3 = platinumValue3,
                            platinumValue4 = platinumValue4,
                            platinumValue5 = platinumValue5,
                            platinumValue6 = platinumValue6,
                            platinumValue7 = platinumValue7,
                            platinumValue8 = platinumValue8,
                            epicValue1 = epicValue1,
                            epicValue2 = epicValue2,
                            epicValue3 = epicValue3,
                            epicValue4 = epicValue4,
                            epicValue5 = epicValue5,
                            epicValue6 = epicValue6,
                            epicValue7 = epicValue7,
                            epicValue8 = epicValue8,
                            userGenerationLevel = userGenerationLevel,
                            userName = userName,
                            userMoneyValue = userMoneyValue,
                            dataStoreManager = dataStoreManager,
                            purchaseValue = 6
                        )
                    } else {
                        Toast.makeText(context, "not enough money", Toast.LENGTH_SHORT).show()
                    }
                },
                image = R.drawable.platinum_2,
                text = R.string.platinum_2,
                price = price_6
            )
        }
    }
}