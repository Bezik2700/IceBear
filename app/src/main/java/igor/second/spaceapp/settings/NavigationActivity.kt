package igor.second.spaceapp.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import igor.second.spaceapp.appwindows.InfoAboutGame
import igor.second.spaceapp.appwindows.collection.MainCollection
import igor.second.spaceapp.appwindows.connection.MainConnection
import igor.second.spaceapp.appwindows.find.MainFind
import igor.second.spaceapp.appwindows.generation.MainGeneration
import igor.second.spaceapp.appwindows.shop.MainShop

sealed class Screens(val route: String){
    data object MainGeneration: Screens("MainGeneration")
    data object MainCollection: Screens("MainCollection")
    data object MainShop: Screens("MainShop")
    data object MainCamera: Screens("MainCamera")
    data object MainConnection: Screens("MainConnection")
    data object InfoAboutGame: Screens("InfoAboutGame")
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
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    navController: NavHostController,
    timerValue: MutableState<String>,
    timerRunning: MutableState<Boolean>
){
    Box(modifier = Modifier){
        Row (
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxSize().padding(top = 48.dp, end = 16.dp)
        ) {
            Text("money: ${userMoneyValue.value}")
        }
        NavHost(
            navController = navController,
            startDestination = Screens.MainGeneration.route,
            modifier = Modifier
                .padding(top = 80.dp, start = 8.dp, end = 8.dp, bottom = 64.dp)
        ) {
            composable (route = Screens.MainGeneration.route) {
                MainGeneration(
                    timerValue = timerValue,
                    timerRunning = timerRunning,
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
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    userGenerationLevel = userGenerationLevel,
                    navController = navController
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
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4
                )
            }
            composable (route = Screens.MainConnection.route) {
                MainConnection()
            }
            composable (route = Screens.MainShop.route) {
                MainShop(
                    dataStoreManager = dataStoreManager
                )
            }
            composable (route = Screens.MainCamera.route) {
                MainFind()
            }
            composable (route = Screens.InfoAboutGame.route) {
                InfoAboutGame()
            }
        }
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
        ) {
            IconButton(onClick = {navController.navigate(Screens.MainCamera.route)}) {
                Icon(Icons.TwoTone.AccountCircle, contentDescription = "camera")
            }
            IconButton(onClick = {navController.navigate(Screens.MainGeneration.route)}) {
                Icon(Icons.TwoTone.Home, contentDescription = "home")
            }
            IconButton(onClick = {navController.navigate(Screens.MainCollection.route)}) {
                Icon(Icons.TwoTone.Favorite, contentDescription = "collection")
            }
            IconButton(onClick = {navController.navigate(Screens.MainShop.route)}) {
                Icon(Icons.TwoTone.ShoppingCart, contentDescription = "shop")
            }
            IconButton(onClick = {navController.navigate(Screens.MainConnection.route)}) {
                Icon(Icons.TwoTone.Share, contentDescription = "connection")
            }
            IconButton(onClick = {navController.navigate(Screens.InfoAboutGame.route)}) {
                Icon(Icons.TwoTone.Info, contentDescription = "info")
            }
        }
    }
}