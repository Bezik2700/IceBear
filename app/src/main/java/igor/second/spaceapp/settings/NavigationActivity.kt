package igor.second.spaceapp.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import igor.second.spaceapp.camera.MainCamera
import igor.second.spaceapp.collection.MainCollection
import igor.second.spaceapp.generation.MainGeneration
import igor.second.spaceapp.shop.MainShop

sealed class Screens(val route: String){
    data object MainGeneration: Screens("MainGeneration")
    data object MainCollection: Screens("MainCollection")
    data object MainShop: Screens("MainShop")
    data object MainCamera: Screens("MainCamera")
}

@Composable
fun NavigationActivity(
    navController: NavHostController,
    timerValue: MutableState<String>,
    timerRunning: MutableState<Boolean>,
    cardValue: MutableState<Int>
){
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    IconButton(onClick = {navController.navigate(Screens.MainCamera.route)}) {
                        Icon(Icons.TwoTone.AccountCircle, contentDescription = "camera")
                    }
                    IconButton(onClick = {navController.navigate(Screens.MainGeneration.route)}) {
                        Icon(Icons.TwoTone.Home, contentDescription = "home")
                    }
                    IconButton(onClick = {navController.navigate(Screens.MainShop.route)}) {
                        Icon(Icons.TwoTone.ShoppingCart, contentDescription = "shop")
                    }
                    IconButton(onClick = {navController.navigate(Screens.MainCollection.route)}) {
                        Icon(Icons.TwoTone.Favorite, contentDescription = "collection")
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.MainGeneration.route,
            modifier = Modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp)
        ) {
            composable (route = Screens.MainGeneration.route) {
                MainGeneration(
                    navController = navController,
                    timerValue = timerValue,
                    timerRunning = timerRunning,
                    cardValue = cardValue
                )
            }
            composable (route = Screens.MainCollection.route) {
                MainCollection()
            }
            composable (route = Screens.MainShop.route) {
                MainShop()
            }
            composable (route = Screens.MainCamera.route) {
                MainCamera()
            }
        }
    }
}