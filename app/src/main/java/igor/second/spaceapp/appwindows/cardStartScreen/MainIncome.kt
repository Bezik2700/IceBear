package igor.second.spaceapp.appwindows.cardStartScreen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appwindows.Screens
import igor.second.spaceapp.appwindows.cardInformation.MainInformation
import igor.second.spaceapp.appwindows.cardStartScreen.dialog.ExitDialogGame
import igor.second.spaceapp.appwindows.cardStartScreen.ratingSetting.UserRating

@Composable
fun MainIncome(
    modifier: Modifier = Modifier,
    navController: NavController,
    dialogShowValue: MutableState<Boolean>,
    dataStoreManager: DataStoreManager,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
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
    epicValue8: MutableState<Int>,
    userName: MutableState<String>
){

    var informationValue by remember { mutableIntStateOf(0) }
    var backPressTime by remember { mutableLongStateOf(0L) }

    var informationEnabled = remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }

    val string = stringResource(R.string.exit_press)

    val context = LocalContext.current

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 64.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Text(userGenerationLevel.value.toString(), color = Color.White)
            UserRating()
            StartGameProcess(
                dialogShowValue = dialogShowValue,
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
                userName = userName,
                dataStoreManager = dataStoreManager,
                userMoneyValue = userMoneyValue,
                userGenerationLevel = userGenerationLevel,
                navController = navController
            )
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth().fillMaxHeight(0.5f)
            ) {
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainShop.route)},
                    onClickInformation = {
                        informationValue = 1
                        informationEnabled.value = true
                                         },
                    name = R.string.shopping_screen,
                    image = R.drawable.income_card_shop,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainGeneration.route)},
                    onClickInformation = {
                        informationValue = 2
                        informationEnabled.value = true
                                         },
                    name = R.string.generation_screen,
                    image = R.drawable.income_card_generation,
                    cardWidth = 1f
                )
            }
            Row {
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainSearching.route)},
                    onClickInformation = {
                        informationValue = 3
                        informationEnabled.value = true
                                         },
                    name = R.string.find_screen,
                    image = R.drawable.income_card_searching,
                    cardWidth = 0.5f
                )
                IncomeCardValue(
                    onClick = {navController.navigate(Screens.MainCollection.route)},
                    onClickInformation = {
                        informationValue = 4
                        informationEnabled.value = true
                                         },
                    name = R.string.collection_screen,
                    image = R.drawable.income_card_collection,
                    cardWidth = 1f
                )
            }
        }
    }
    if (informationEnabled.value){
        MainInformation(
            informationEnabled = informationEnabled,
            dialogText = when (informationValue){
                1 -> R.string.bronze1
                2 -> R.string.bronze2
                3 -> R.string.bronze3
                else -> R.string.bronze4
            },
            dialogImage = when (informationValue){
                1 -> R.drawable.bronze_1
                2 -> R.drawable.bronze_2
                3 -> R.drawable.bronze_3
                else -> R.drawable.bronze_4
            },
            navController = navController
        )
    }

    if (showExitDialog) {
        ExitDialogGame(
            onConfirm = { (context as? Activity)?.finish() },
            onDismiss = { showExitDialog = false }
        )
    }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressTime > 2000) {
            backPressTime = currentTime
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        } else {
            showExitDialog = true
        }
    }
}
