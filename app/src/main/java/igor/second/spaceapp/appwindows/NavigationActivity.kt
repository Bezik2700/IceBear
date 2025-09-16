package igor.second.spaceapp.appwindows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import igor.second.spaceapp.R
import igor.second.spaceapp.appsettings.DataStoreManager
import igor.second.spaceapp.appsettings.TopRowNavigation
import igor.second.spaceapp.appsettings.UserInfoDialog
import igor.second.spaceapp.appwindows.cardCollection.MainCollection
import igor.second.spaceapp.appwindows.cardGeneration.MainGeneration
import igor.second.spaceapp.appwindows.cardSearching.MainSearching
import igor.second.spaceapp.appwindows.cardShopping.MainShop
import igor.second.spaceapp.appwindows.cardStartScreen.MainIncome
import igor.second.spaceapp.appwindows.gameProcess.MainConnection

sealed class Screens(val route: String){
    data object MainGeneration: Screens("MainGeneration")
    data object MainShop: Screens("MainShop")
    data object MainSearching: Screens("MainSearching")
    data object MainCollection: Screens("MainCollection")
    data object MainConnection: Screens("MainConnection")
    data object MainIncome: Screens("MainIncome")
}

@Composable
fun NavigationActivity(
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
    navController: NavHostController,
    userName: MutableState<String>
){
    Box(modifier = Modifier){

        var dialogShowValue = remember { mutableStateOf(false) }

        if (dialogShowValue.value){
            UserInfoDialog(
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
                userGenerationLevel = userGenerationLevel
            )
        }

        Image(
            painterResource(R.drawable.income_fon),
            contentDescription = "income fon",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // top row user info and money
        TopRowNavigation(
            dialogShowValue = dialogShowValue,
            userMoneyValue = userMoneyValue,
            userName = userName
        )

        NavHost(
            navController = navController,
            startDestination = Screens.MainIncome.route,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            composable (route = Screens.MainGeneration.route) {
                MainGeneration(
                    dataStoreManager = dataStoreManager,
                    userMoneyValue = userMoneyValue,
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
                    navController = navController,
                    userName = userName
                )
            }
            composable (route = Screens.MainCollection.route) {
                MainCollection(
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
                    navController = navController
                )
            }
            composable (route = Screens.MainConnection.route) {
                MainConnection(
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
                    userMoneyValue = userMoneyValue,
                    userGenerationLevel = userGenerationLevel,
                    dataStoreManager = dataStoreManager,
                    userName = userName,
                    navController = navController
                )
            }
            composable (route = Screens.MainShop.route) {
                MainShop(
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
                    userMoneyValue = userMoneyValue,
                    userGenerationLevel = userGenerationLevel,
                    dataStoreManager = dataStoreManager,
                    userName = userName,
                    navController = navController
                )
            }
            composable (route = Screens.MainSearching.route) {
                MainSearching(
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
                    userMoneyValue = userMoneyValue,
                    userGenerationLevel = userGenerationLevel,
                    dataStoreManager = dataStoreManager,
                    userName = userName,
                    navController = navController
                )
            }
            composable (route = Screens.MainIncome.route) {
                MainIncome(
                    navController = navController
                )
            }
        }
    }
}